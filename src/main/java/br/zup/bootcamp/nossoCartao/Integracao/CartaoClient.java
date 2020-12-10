package br.zup.bootcamp.nossoCartao.Integracao;

import br.zup.bootcamp.nossoCartao.Integracao.Request.BloqueioRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.BloqueioResponse;
import br.zup.bootcamp.nossoCartao.Integracao.Response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cartao", url = "${cartao.host}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    CartaoResponse cartao(@RequestParam String idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueio(@PathVariable String id, @RequestBody BloqueioRequest bloqueioRequest);
}