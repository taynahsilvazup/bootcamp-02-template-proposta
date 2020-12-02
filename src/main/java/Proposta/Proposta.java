package Proposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "PROPOSTA")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DOCUMENTO")
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
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
}
