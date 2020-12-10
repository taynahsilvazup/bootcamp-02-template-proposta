package br.zup.bootcamp.nossoCartao.Carteira;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Cartao.CartaoRepository;
import br.zup.bootcamp.nossoCartao.Integracao.CartaoClient;
import br.zup.bootcamp.nossoCartao.Integracao.Request.CarteirasRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.CarteirasResponse;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoClient cartaoClient;

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    CarteiraRepository carteiraRepository;

    @PostMapping("/{cartaoId}/carteiras")
    public ResponseEntity<?> carteira(@PathVariable UUID cartaoId, @RequestBody @Valid CarteirasRequest carteirasRequest, UriComponentsBuilder builder) {
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        }
        Optional<Carteira> carteiraOptional = carteiraRepository.findByCarteiraAndCartao(carteirasRequest.getCarteira(), cartao.get());
        if(carteiraOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão " + cartaoId + " já possui uma carteira " + carteirasRequest.getCarteira() + " associada.");
        }

        CarteirasResponse response = cartaoClient.carteira(cartao.get().getNumero(), carteirasRequest);

        if(response.isCreated()) {
            Carteira carteira = carteirasRequest.toModel(cartao.get());
            executorTransacao.salvaEComita(carteira);

            URI location = builder.path("cartoes/{cartaoId}/carteiras/{carteiraId}")
                    .buildAndExpand(cartaoId, carteira.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu algo de errado ao criar a carteira.");
    }

}
