package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Integracao.AnaliseClient;
import br.zup.bootcamp.nossoCartao.Integracao.Request.AnaliseRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.AnaliseResponse;
import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import br.zup.bootcamp.nossoCartao.Validator.VerificaCpfCnpjValidator;
import feign.FeignException.FeignServerException;
import feign.FeignException.FeignClientException;
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
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

/*    @Autowired
    private ExecutorTransacao executorTransacao;*/

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private AnaliseClient analiseClient;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new VerificaCpfCnpjValidator());
    }

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder) {
        Proposta possivelProposta = propostaRepository.findByDocumento(request.getDocumento());

        if(possivelProposta != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadastrado em outra proposta.");
        }

        Proposta proposta = request.toModel();
        manager.persist(proposta);

        String documento = proposta.getDocumento();
        String nome = proposta.getNome();
        String idProposta = proposta.getId().toString();

        try {
            AnaliseResponse response = analiseClient.analiseProposta(new AnaliseRequest(documento, nome, idProposta));
            if (response.semRestricao()) {
                proposta.atualizaStatus(StatusPropostaEnum.ELEGIVEL);
            }
            manager.merge(proposta);
        } catch (FeignClientException e) {
            proposta.atualizaStatus(StatusPropostaEnum.NAO_ELEGIVEL);
            manager.merge(proposta);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF se encontra com pendências.");
        } catch (FeignServerException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Por favor, verifique seus dados.");
        }

        URI location = builder.path("/proposta/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}