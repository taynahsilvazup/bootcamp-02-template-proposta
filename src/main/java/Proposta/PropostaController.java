package Proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping("/proposta")
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder) {
        Proposta proposta = request.toModel();

        URI location = builder.path("/proposta/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}