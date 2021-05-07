package br.com.ot4.proposta.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.ot4.proposta.proposta.Proposta;

@Component
public class LoggerProposta {
	
	private final Logger logger = LoggerFactory.getLogger(Proposta.class);

	public void criarProposta(Proposta proposta) {
		logger.warn("Log de aviso, algo está errado ou faltando! cuidado!");
        logger.error("Log de erro, algo de errado aconteceu!");
        logger.debug("Log de depuração, contém informações mais refinadas, que são mais úteis para depurar um aplicativo");
        logger.trace("Log de rastreabilidade, contém informações mais refinadas do que o DEBUG");
        logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(), proposta.getSalario());
	}

}
