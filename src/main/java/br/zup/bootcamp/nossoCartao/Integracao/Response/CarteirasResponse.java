package br.zup.bootcamp.nossoCartao.Integracao.Response;

public class CarteirasResponse {

    private String resultado;

    private String id;

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    @Deprecated
    public CarteirasResponse() {
    }

    public CarteirasResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public boolean isCreated() {
        return resultado.equals("ASSOCIADA");
    }
}
