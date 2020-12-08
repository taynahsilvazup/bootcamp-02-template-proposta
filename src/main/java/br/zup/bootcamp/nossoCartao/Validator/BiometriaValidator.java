package br.zup.bootcamp.nossoCartao.Validator;

import br.zup.bootcamp.nossoCartao.Biometria.NovaBiometriaRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BiometriaValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaBiometriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors())
            return;

        NovaBiometriaRequest request = (NovaBiometriaRequest) o;

        if(request.biometriaValida()){
            errors.rejectValue("biometria", null, "Biometria precisa ser em Base64");
        }
    }
}
