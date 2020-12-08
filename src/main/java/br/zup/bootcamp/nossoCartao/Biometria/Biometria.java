package br.zup.bootcamp.nossoCartao.Biometria;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "BIOMETRIA")
    @NotNull
    private byte[] biometria;

    @JoinColumn(name = "CARTAO_ID")
    @ManyToOne
    @NotNull
    private Cartao cartao;

    @Column(name = "DATA_CADASTRO")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public byte[] getBiometria() {
        return biometria;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public Biometria() {
    }

    public Biometria(byte[] biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }
}
