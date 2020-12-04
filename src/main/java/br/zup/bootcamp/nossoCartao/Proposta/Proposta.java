package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Proposta.Converter.StatusPropostaConverter;
import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROPOSTA")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DOCUMENTO", unique = true)
    @NotBlank
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

    @Column(name = "DATA_CADASTRO")
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
}
