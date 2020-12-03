package br.zup.bootcamp.nossoCartao.Validator;

import br.zup.bootcamp.nossoCartao.Proposta.NovaPropostaRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificaCpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaPropostaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaPropostaRequest request = (NovaPropostaRequest) o;
        if(!request.documentoValido()) {
            errors.rejectValue("Documento", null, "Informe CPF ou CNPJ.");
        }
    }
}
