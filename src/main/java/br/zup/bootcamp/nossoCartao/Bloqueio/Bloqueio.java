package br.zup.bootcamp.nossoCartao.Bloqueio;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Bloqueio {

    @Id
    @Type(type = "uuid-char")
    private final UUID id = UUID.randomUUID();

    @JoinColumn(name = "CARTAO_ID")
    @NotNull
    @OneToOne
    private Cartao cartao;

    @Column(name = "CLIENTE_IP")
    @NotBlank
    private String clienteIp;

    @Column(name = "USER_AGENT")
    @NotBlank
    private String userAgent;

    @Column(name = "DATA_BLOQUEIO")
    @NotNull
    private final LocalDateTime dataBloqueio = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getClienteIp() {
        return clienteIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(@NotNull Cartao cartao, @NotBlank String clienteIp, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.clienteIp = clienteIp;
        this.userAgent = userAgent;
    }
}
