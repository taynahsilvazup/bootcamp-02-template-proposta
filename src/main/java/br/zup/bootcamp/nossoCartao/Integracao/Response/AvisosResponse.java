package br.zup.bootcamp.nossoCartao.Integracao.Response;

public class AvisosResponse {

    private String resultado;

    public String getResultado() {
        return resultado;
    }

    @Deprecated
    public AvisosResponse() {
    }

    public AvisosResponse(String resultado) {
        this.resultado = resultado;
    }

    public boolean isCreated() {
        return resultado.equals("CRIADO");
    }
}
