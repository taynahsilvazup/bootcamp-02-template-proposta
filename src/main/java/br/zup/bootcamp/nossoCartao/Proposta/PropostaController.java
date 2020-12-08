package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Integracao.AnaliseClient;
import br.zup.bootcamp.nossoCartao.Integracao.Request.AnaliseRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.AnaliseResponse;
import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import br.zup.bootcamp.nossoCartao.Proposta.Request.NovaPropostaRequest;
import br.zup.bootcamp.nossoCartao.Proposta.Response.BuscaPropostaResponse;
import br.zup.bootcamp.nossoCartao.Transacao.ExecutorTransacao;
import br.zup.bootcamp.nossoCartao.Validator.VerificaCpfCnpjValidator;
import feign.FeignException.FeignServerException;
import feign.FeignException.FeignClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;

    @Autowired
    private AnaliseClient analiseClient;

    private static final Logger log = LoggerFactory.getLogger(PropostaController.class);

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new VerificaCpfCnpjValidator());
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder) {
        Proposta possivelProposta = propostaRepository.findByDocumento(request.getDocumento());

        if(possivelProposta != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadastrado em outra proposta.");
        }

        Proposta proposta = request.toModel();
        executorTransacao.salvaEComita(proposta);

        log.info("Proposta {} criada com sucesso.", proposta.getId());

        String documento = proposta.getDocumento();
        String nome = proposta.getNome();
        String idProposta = proposta.getId().toString();

        try {
            AnaliseResponse response = analiseClient.analiseProposta(new AnaliseRequest(documento, nome, idProposta));
            if (response.semRestricao()) {
                proposta.atualizaStatus(StatusPropostaEnum.ELEGIVEL);
            }
            executorTransacao.atualizaEComita(proposta);
            log.info("Proposta [{}] aguardando criação de cartão.", proposta.getId());
        } catch (FeignClientException e) {
            proposta.atualizaStatus(StatusPropostaEnum.NAO_ELEGIVEL);
            executorTransacao.atualizaEComita(proposta);
            log.info("Proposta [{}] não elegível para criação de cartão.", proposta.getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF se encontra com pendências.");
        } catch (FeignServerException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Por favor, verifique seus dados.");
        }

        URI location = builder.path("/proposta/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> buscaProposta(@PathVariable UUID id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if(proposta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada proposta com o id " +id);
        }
        BuscaPropostaResponse response = new BuscaPropostaResponse(proposta.get());

        return ResponseEntity.ok(response);
    }
}