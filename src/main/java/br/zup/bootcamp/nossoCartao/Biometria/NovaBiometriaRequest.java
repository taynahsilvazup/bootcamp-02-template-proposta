package br.zup.bootcamp.nossoCartao.Biometria;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    private String biometria;

    public String getBiometria() {
        return biometria;
    }

    @Deprecated
    public NovaBiometriaRequest() {
    }

    public boolean biometriaValida() {
        Assert.hasLength(biometria, "Biometria vazia.");
        return Base64.isBase64(biometria);
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(Base64.decodeBase64(biometria), cartao);
    }
}