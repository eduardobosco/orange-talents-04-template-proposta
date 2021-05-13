package br.com.ot4.proposta.proposta.Analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${client.analise.url}", name = "${client.analise.name}")
@Component
public interface AnalisePropostaFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    public AnalisePropostaResponse analisaProposta(AnalisePropostaRequest request);

}
