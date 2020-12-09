package br.zup.bootcamp.nossoCartao.Biometria;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Cartao.CartaoRepository;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import br.zup.bootcamp.nossoCartao.Validator.BiometriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ExecutorTransacao executorTransacao;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new BiometriaValidator());
    }

    @PostMapping("/{cartaoId}")
    public ResponseEntity<?> cadastraBiometria(@RequestBody @Valid NovaBiometriaRequest novaBiometriaRequest, @PathVariable UUID cartaoId, UriComponentsBuilder builder){
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);

        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        }

        Biometria biometria = novaBiometriaRequest.toModel(cartao.get());
        executorTransacao.salvaEComita(biometria);

        URI location = builder
                .path("/biometrias/{id}")
                .buildAndExpand(biometria.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}