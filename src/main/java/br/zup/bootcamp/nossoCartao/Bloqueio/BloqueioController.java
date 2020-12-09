package br.zup.bootcamp.nossoCartao.Bloqueio;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Cartao.CartaoRepository;
import br.zup.bootcamp.nossoCartao.Cartao.Enum.StatusCartaoEnum;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
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

    @PostMapping("/cartoes/{cartaoId}/bloqueios")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable UUID cartaoId, HttpServletRequest request, UriComponentsBuilder builder) {
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        }

        if(bloqueioRepository.findByCartao(cartao.get()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já bloqueado.");
        }

        Bloqueio bloqueio = new Bloqueio(cartao.get(), request.getRemoteAddr(), request.getHeader("User-Agent"));
        executorTransacao.salvaEComita(bloqueio);

        cartao.get().atualizaStatus(StatusCartaoEnum.BLOQUEADO);
        executorTransacao.atualizaEComita(cartao.get());

        return ResponseEntity.ok().build();
    }
}
