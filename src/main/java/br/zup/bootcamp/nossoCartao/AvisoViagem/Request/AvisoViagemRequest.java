package br.zup.bootcamp.nossoCartao.AvisoViagem.Request;

import br.zup.bootcamp.nossoCartao.AvisoViagem.AvisoViagem;
import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    @Deprecated
    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(@NotBlank String destino,
                              @NotNull @FutureOrPresent LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public AvisoViagem toModel(Cartao cartao, String clientIp, String userAgent) {
        return new AvisoViagem(cartao, destino, dataTermino, clientIp, userAgent);
    }
}
