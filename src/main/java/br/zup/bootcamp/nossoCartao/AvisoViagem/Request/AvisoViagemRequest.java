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
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Deprecated
    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(@NotBlank String destino,
                              @NotNull @FutureOrPresent LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoViagem toModel(Cartao cartao, String clientIp, String userAgent) {
        return new AvisoViagem(cartao, destino, validoAte, clientIp, userAgent);
    }
}
