package br.zup.bootcamp.nossoCartao.Carteira;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Carteira {

    @Id
    @Type(type = "uuid-char")
    private final UUID id = UUID.randomUUID();

    @JoinColumn(name = "CARTAO_ID")
    @ManyToOne
    @NotNull
    private Cartao cartao;

    @Column(name = "CARTEIRA")
    @NotBlank
    private String carteira;

    @Column(name = "DATA_CADASTRO")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getCarteira() {
        return carteira;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public Carteira() {
    }

    public Carteira(@NotNull Cartao cartao,
                    @NotBlank String carteira) {
        this.cartao = cartao;
        this.carteira = carteira;
    }
}
