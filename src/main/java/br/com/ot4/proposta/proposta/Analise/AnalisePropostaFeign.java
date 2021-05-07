package br.com.ot4.proposta.proposta.Analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "solicitacao", url = "http://127.0.0.1:9999/api")
@Component
public interface AnalisePropostaFeign {

	@PostMapping(value = "/solicitacao", produces = "application/json")
    public AnalisePropostaResponse analisaProposta(AnalisePropostaRequest request);

}
