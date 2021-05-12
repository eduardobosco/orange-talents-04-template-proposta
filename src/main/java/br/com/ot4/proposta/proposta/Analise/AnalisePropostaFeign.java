package br.com.ot4.proposta.proposta.Analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "${proposta.host}")
@Component
public interface AnalisePropostaFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    public AnalisePropostaResponse analisaProposta(AnalisePropostaRequest request);

}
