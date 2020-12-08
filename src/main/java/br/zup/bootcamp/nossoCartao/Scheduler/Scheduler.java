package br.zup.bootcamp.nossoCartao.Scheduler;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Integracao.CartaoClient;
import br.zup.bootcamp.nossoCartao.Integracao.Response.CartaoResponse;
import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import br.zup.bootcamp.nossoCartao.Proposta.Proposta;
import br.zup.bootcamp.nossoCartao.Proposta.PropostaRepository;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private ExecutorTransacao executorTransacao;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CartaoClient cartaoClient;

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedDelay = 50000)
    @Transactional
    public void verificaCriacaoCartao() {
        List<Proposta> propostas = propostaRepository.findByStatusAndCartaoIsNull(StatusPropostaEnum.ELEGIVEL);

        log.info("Existem {} proposta(s) aguardando cartão.", propostas.size());

        for(Proposta proposta : propostas) {
                CartaoResponse response = cartaoClient.cartao(proposta.getId().toString());
                proposta.vinculaCartao(response.getId());
                executorTransacao.atualizaEComita(proposta);

                Cartao cartao = new Cartao(response.getId());
                executorTransacao.salvaEComita(cartao);

                log.info("Cartao vinculado a proposta [{}].", proposta.getId());

        }
    }
}
