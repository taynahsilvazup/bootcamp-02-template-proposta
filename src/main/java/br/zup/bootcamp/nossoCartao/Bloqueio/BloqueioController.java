package br.zup.bootcamp.nossoCartao.Bloqueio;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Cartao.CartaoRepository;
import br.zup.bootcamp.nossoCartao.Cartao.Enum.StatusCartaoEnum;
import br.zup.bootcamp.nossoCartao.Integracao.CartaoClient;
import br.zup.bootcamp.nossoCartao.Integracao.Request.BloqueioRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.BloqueioResponse;
import br.zup.bootcamp.nossoCartao.Proposta.PropostaController;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BloqueioController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    BloqueioRepository bloqueioRepository;

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    CartaoClient cartaoClient;

    private static final Logger log = LoggerFactory.getLogger(BloqueioController.class);

    @PostMapping("/cartoes/{cartaoId}/bloqueios")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable UUID cartaoId, HttpServletRequest request, UriComponentsBuilder builder) {
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        }

        if(bloqueioRepository.findByCartao(cartao.get()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já bloqueado.");
        }

        BloqueioResponse response = cartaoClient.bloqueio(cartao.get().getNumero(), new BloqueioRequest(request.getRemoteUser()));

        if(response.isBloqueado()) {
            //registra bloqueio
            Bloqueio bloqueio = new Bloqueio(cartao.get(), request.getRemoteAddr(), request.getHeader("User-Agent"));
            executorTransacao.salvaEComita(bloqueio);

            //avisa legado
            cartao.get().atualizaStatus(StatusCartaoEnum.BLOQUEADO);
            executorTransacao.atualizaEComita(cartao.get());

            log.info("Cartão [{}] bloqueado. Identificador do bloqueio: [{}]", cartao.get().getId(), bloqueio.getId());

            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu uma falha no sistema de bloqueio.");
    }
}