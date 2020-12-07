package br.zup.bootcamp.nossoCartao.Integracao;

import br.zup.bootcamp.nossoCartao.Integracao.Response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartao", url = "${cartao.host}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    CartaoResponse cartao(@RequestParam String idProposta);
}