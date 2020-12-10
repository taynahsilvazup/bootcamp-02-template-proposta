package br.zup.bootcamp.nossoCartao.Integracao.Response;

public class BloqueioResponse {

    private String resultado;

    public String getResultado() {
        return resultado;
    }

    @Deprecated
    public BloqueioResponse() {
    }

    public BloqueioResponse(String resultado) {
        this.resultado = resultado;
    }

    public boolean isBloqueado() {
        return resultado.equals("BLOQUEADO");
    }
}
