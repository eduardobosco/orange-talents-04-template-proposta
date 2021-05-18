package br.com.ot4.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ot4.proposta.bloqueio.BloqueioRequest;
import br.com.ot4.proposta.bloqueio.BloqueioResponse;
import br.com.ot4.proposta.viagem.ViagemRequest;

@FeignClient(url = "${client.contas.url}", name = "${client.contas.name}")
public interface CartaoClient {

	/*
	 * Executa uma chamada GET na rota /api/cartoes do serviço de accounts,
	 * retornando a resposta como um String.
	 * 
	 * @param idProposta id da proposta para qual procura-se um cartão.
	 * 
	 * @return CartaoClientResponse objeto representando o cartão encontrado.
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/api/cartoes", consumes = "application/json")
	public SolicitacaoCartaoResponse cartaoParaProposta(@RequestParam(name = "idProposta") String idProposta);

	@PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueio(@PathVariable String id, @RequestBody BloqueioRequest request);
	
}