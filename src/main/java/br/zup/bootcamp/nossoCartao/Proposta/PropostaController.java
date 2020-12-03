package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Validator.VerificaCpfCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PersistenceContext
    EntityManager manager;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new VerificaCpfCnpjValidator());
    }

    @PostMapping("/proposta")
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder) {
        Proposta possivelProposta = propostaRepository.findByDocumento(request.getDocumento());

        if(possivelProposta != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadastrado em outra proposta.");
        }

        Proposta proposta = request.toModel();

        URI location = builder.path("/proposta/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}