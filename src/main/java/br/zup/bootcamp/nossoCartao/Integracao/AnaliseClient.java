package br.zup.bootcamp.nossoCartao.Integracao;

import br.zup.bootcamp.nossoCartao.Integracao.Request.AnaliseRequest;
import br.zup.bootcamp.nossoCartao.Integracao.Response.AnaliseResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analise", url = "${analise.host}")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    AnaliseResponse analiseProposta(@RequestBody AnaliseRequest request);
}
