package br.zup.bootcamp.nossoCartao.Integracao.Response;

public class CartaoResponse {

    private String id;

    public String getId() {
        return id;
    }

    @Deprecated
    public CartaoResponse() {
    }

    public CartaoResponse(String id) {
        this.id = id;
    }
}
