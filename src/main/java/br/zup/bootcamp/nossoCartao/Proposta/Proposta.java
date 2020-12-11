package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Proposta.Converter.StatusPropostaConverter;
import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import br.zup.bootcamp.nossoCartao.Util.CryptoConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PROPOSTA")
public class Proposta {

    @Id
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "DOCUMENTO", unique = true)
    @NotBlank
    @Convert(converter = CryptoConverter.class)
    private String documento;

    @Column(name = "EMAIL")
    @NotBlank
    @Email
    private String email;

    @Column(name = "NOME")
    @NotBlank
    private String nome;

    @Column(name = "ENDERECO")
    @NotBlank
    private String endereco;

    @Column(name = "SALARIO")
    @NotNull
    @Positive
    private BigDecimal salario;

    @Column(name = "STATUS")
    @Convert(converter = StatusPropostaConverter.class)
    private StatusPropostaEnum status;

    @Column(name = "CARTAO")
    private String cartao;

    @Column(name = "DATA_CADASTRO")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public UUID getId() {
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
    public Proposta() {
    }

    public Proposta(@NotBlank String documento,
                    @NotBlank @Email String email,
                    @NotBlank String nome, @NotBlank String endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void atualizaStatus(StatusPropostaEnum status) {
        this.status = status;
    }

    public void vinculaCartao(String cartao) {
        this.cartao = cartao;
    }
}
