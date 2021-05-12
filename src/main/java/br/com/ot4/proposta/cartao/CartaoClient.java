package br.com.ot4.proposta.cartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartaoClient", url = "${cartoes.host}")
public interface CartaoClient {

	
	/*
     *  Executa uma chamada GET na rota /api/cartoes do serviço de accounts,
     * retornando a resposta como um String.
     * @param idProposta id da proposta para qual procura-se um cartão.
     * @return CartaoClientResponse objeto representando o cartão encontrado.
     */
	
	 @GetMapping
	    public SolicitacaoCartaoResponse cartaoParaProposta(@RequestParam(name = "idProposta") String idProposta);

}