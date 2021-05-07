package br.com.ot4.proposta.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaRequest> cadastrar(@RequestBody @Valid PropostaRequest form, UriComponentsBuilder uriBuilder) {
		Proposta proposta = form.converter(propostaRepository);
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PropostaRequest());
	}

}
