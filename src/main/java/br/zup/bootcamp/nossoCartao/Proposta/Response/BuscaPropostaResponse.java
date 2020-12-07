package br.zup.bootcamp.nossoCartao.Proposta.Response;

import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import br.zup.bootcamp.nossoCartao.Proposta.Proposta;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BuscaPropostaResponse {

    private Long id;

    private String documento;

    private String email;

    private String nome;

    private String endereco;

    private BigDecimal salario;

    private StatusPropostaEnum status;

    private String cartao;

    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusPropostaEnum getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public BuscaPropostaResponse() {
    }

    public BuscaPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.cartao = proposta.getCartao();
    }
}
