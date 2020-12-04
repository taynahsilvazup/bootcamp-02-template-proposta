package br.zup.bootcamp.nossoCartao.Integracao.Request;

public class AnaliseRequest {

    private String documento;
    private String nome;
    private String idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Deprecated
    public AnaliseRequest() {
    }

    public AnaliseRequest(String documento,
                          String nome,
                          String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }
}
