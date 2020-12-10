package br.zup.bootcamp.nossoCartao.AvisoViagem;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "AVISO_VIAGEM")
public class AvisoViagem {

    @Id
    @Type(type = "uuid-char")
    private final UUID id = UUID.randomUUID();

    @JoinColumn(name = "CARTAO_ID")
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Column(name = "DESTINO")
    @NotBlank
    private String destino;

    @Column(name = "DATA_TERMINO")
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    @Column(name = "CLIENTE_IP")
    private String clienteIp;

    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "DATA_CADASTRO")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public String getClienteIp() {
        return clienteIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(@NotNull Cartao cartao,
                       @NotBlank String destino,
                       @NotNull @FutureOrPresent LocalDate dataTermino,
                       String clienteIp,
                       String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.clienteIp = clienteIp;
        this.userAgent = userAgent;
    }
}
