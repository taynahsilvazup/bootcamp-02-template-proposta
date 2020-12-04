package br.zup.bootcamp.nossoCartao.Integracao.Response;

public class AnaliseResponse {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Deprecated
    public AnaliseResponse() {
    }

    public AnaliseResponse(String documento,
                           String nome,
                           String resultadoSolicitacao,
                           String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public boolean semRestricao() {
        return resultadoSolicitacao.equals("SEM_RESTRICAO");
    }
}