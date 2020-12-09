package br.zup.bootcamp.nossoCartao.Cartao;

import br.zup.bootcamp.nossoCartao.Cartao.Converter.StatusCartaoConverter;
import br.zup.bootcamp.nossoCartao.Cartao.Enum.StatusCartaoEnum;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
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

    @Column(name = "STATUS")
    @Convert(converter = StatusCartaoConverter.class)
    private StatusCartaoEnum status = StatusCartaoEnum.ATIVO;


    public UUID getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public StatusCartaoEnum getStatus() {
        return status;
    }

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, StatusCartaoEnum status) {
        this.numero = numero;
        this.status = status;
    }

    public void atualizaStatus(StatusCartaoEnum status) {
        this.status = status;
    }
}
