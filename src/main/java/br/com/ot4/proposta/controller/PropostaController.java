package br.com.ot4.proposta.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ot4.proposta.proposta.Proposta;
import br.com.ot4.proposta.proposta.PropostaRequest;
import br.com.ot4.proposta.repository.PropostaRepository;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest form,
			UriComponentsBuilder uriBuilder) {

		logger.info("Request de Proposta do cliente={} com salario={} e email={} recebida.", form.getDocumento(), form.getSalario().toString(), form.getEmail());
		
		Optional<Proposta> optional = propostaRepository.findByDocumento(form.getDocumento());

		if (optional.isPresent()) {
			logger.warn("Proposta com documento já existente recebida. Documento={}", form.getDocumento());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma proposta para esse solicitante.");

		}
		Proposta proposta = form.converter(propostaRepository);
		propostaRepository.save(proposta);
		
		logger.info("Proposta criada={}", proposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PropostaRequest());
	}

}
