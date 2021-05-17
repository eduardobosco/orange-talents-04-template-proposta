package br.com.ot4.proposta.scheduler;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ot4.proposta.cartao.CartaoClient;
import br.com.ot4.proposta.cartao.SolicitacaoCartaoResponse;
import br.com.ot4.proposta.proposta.Proposta;
import br.com.ot4.proposta.proposta.PropostaController;
import br.com.ot4.proposta.proposta.PropostaRepository;
import feign.FeignException;

@Component
public class PropostaCartaoScheduler {

	@Autowired
	private CartaoClient cartaoClient;
	@Autowired
	private PropostaRepository propostaRepository;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	
	public PropostaCartaoScheduler(PropostaRepository propostaRepository, CartaoClient cartaoClient) {
        this.propostaRepository = propostaRepository;
        this.cartaoClient = cartaoClient;
    }

	@Transactional
	@Scheduled(fixedDelay = 60000)
	public void checarProposta() {
		
		List<Proposta> propostasEmAberto = propostaRepository.findAllWhereCartaoIdIsNull();

		logger.info("Iniciado Verificação de Propostas sem Cartão atribuido");
		
		for (Proposta proposta : propostasEmAberto) {
			try {
				SolicitacaoCartaoResponse novoCartao = cartaoClient.cartaoParaProposta(proposta.getId().toString());
				
				proposta.adicionaCartao(novoCartao.toModel(proposta));
				
				propostaRepository.save(proposta);
				
				logger.info("Cartão {} atribuido a proposta {}", novoCartao.getId(), proposta.getId());

			} catch (FeignException.FeignClientException.InternalServerError e) {
				
				logger.info("Proposta ainda sem cartao para associar" + e);
			}
		}

	}

}
