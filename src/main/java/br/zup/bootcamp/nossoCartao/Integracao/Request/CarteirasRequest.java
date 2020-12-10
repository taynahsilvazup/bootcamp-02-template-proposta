package br.zup.bootcamp.nossoCartao.Integracao.Request;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import br.zup.bootcamp.nossoCartao.Carteira.Carteira;
import br.zup.bootcamp.nossoCartao.Validator.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteirasRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Unique(domainClass = Carteira.class, fieldName = "carteira", message = "Carteira já vinculada à esse cartão.")
    private String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    @Deprecated
    public CarteirasRequest() {
    }

    public CarteirasRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(cartao, carteira);
    }
}
