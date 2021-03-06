package br.zup.bootcamp.nossoCartao.AvisoViagem;

import br.zup.bootcamp.nossoCartao.AvisoViagem.Request.AvisoViagemRequest;
import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Cartao.CartaoRepository;
import br.zup.bootcamp.nossoCartao.Integracao.CartaoClient;
import br.zup.bootcamp.nossoCartao.Integracao.Response.AvisosResponse;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class AvisoViagemController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    CartaoClient cartaoClient;

    @PostMapping("/{cartaoId}/avisoViagem")
    public ResponseEntity<?> avisoviagem(@PathVariable UUID cartaoId, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request) {
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        }

        AvisosResponse response = cartaoClient.aviso(cartao.get().getNumero(), avisoViagemRequest);

        if(response.isCreated()) {
            AvisoViagem avisoViagem = avisoViagemRequest.toModel(cartao.get(), request.getRemoteAddr(), request.getHeader("User-Agent"));
            executorTransacao.salvaEComita(avisoViagem);
            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo de errado ocorreu no sistema de avisos.");
    }
}
