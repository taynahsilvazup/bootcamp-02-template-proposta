package br.zup.bootcamp.nossoCartao.Integracao;

import br.zup.bootcamp.nossoCartao.AvisoViagem.Request.AvisoViagemRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Request.BloqueioRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Request.CarteirasRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.AvisosResponse;
import br.zup.bootcamp.nossoCartao.Integracao.Response.BloqueioResponse;
import br.zup.bootcamp.nossoCartao.Integracao.Response.CartaoResponse;
import br.zup.bootcamp.nossoCartao.Integracao.Response.CarteirasResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cartao", url = "${cartao.host}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    CartaoResponse cartao(@RequestParam String idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueio(@PathVariable String id, @RequestBody BloqueioRequest bloqueioRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/avisos")
    AvisosResponse aviso(@PathVariable String id, @RequestBody AvisoViagemRequest avisosRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/carteiras")
    CarteirasResponse carteira(@PathVariable String id, @RequestBody CarteirasRequest carteirasRequest);

}