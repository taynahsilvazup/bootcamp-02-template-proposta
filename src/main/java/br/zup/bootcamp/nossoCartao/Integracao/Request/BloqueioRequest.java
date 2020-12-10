package br.zup.bootcamp.nossoCartao.Integracao.Request;

public class BloqueioRequest {

    private String sistemaResponsavel;

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    @Deprecated
    public BloqueioRequest() {
    }

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
