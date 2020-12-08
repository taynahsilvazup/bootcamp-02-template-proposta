package br.zup.bootcamp.nossoCartao.Cartao;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Cartao {

    @Id
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "DATA_CADASTRO")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero) {
        this.numero = numero;
    }
}
