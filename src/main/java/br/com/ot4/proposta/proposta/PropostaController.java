package br.com.ot4.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ot4.proposta.proposta.Analise.AnalisePropostaFeign;
import br.com.ot4.proposta.proposta.Analise.AnalisePropostaRequest;
import br.com.ot4.proposta.proposta.Analise.AnalisePropostaResponse;
import br.com.ot4.proposta.proposta.Analise.RetornoAnalise;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private AnalisePropostaFeign analisePropostaFeign;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	private final Tracer tracer;

    public PropostaController(Tracer tracer) {
        this.tracer = tracer;
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> consultaProposta(@PathVariable("id") Long id){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if(proposta.isPresent()){
			return ResponseEntity.ok(new PropostaResponse(proposta));
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	} 


	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest form,
			UriComponentsBuilder uriBuilder) {

		logger.info("Requisição de Proposta do cliente {} com salario {} e email {} recebida.", form.getDocumento(), form.getSalario().toString(), form.getEmail());
		
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", form.getEmail());

        activeSpan.setBaggageItem("user.email", form.getEmail());

        activeSpan.log("Meu log personalizado - Eduardo");
		
		Optional<Proposta> optional = propostaRepository.findByDocumento(form.getDocumento());

		if (optional.isPresent()) {
			logger.warn("Proposta com documento já existente recebida. Documento {}", form.getDocumento());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma proposta para esse solicitante.");

		}
		Proposta proposta = form.converter(propostaRepository);
		
		propostaRepository.save(proposta);
		
		analisaRestricao(proposta);
		
		logger.info("Proposta criada {} para o cliente {}", proposta.getId(), form.getDocumento());
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
	}

	public void analisaRestricao(Proposta proposta){
        logger.info("Enviando analise da proposta de id {}", proposta.getId());
        StatusAnalisaProposta status;
        try{
            AnalisePropostaResponse response = analisePropostaFeign.analisaProposta(new AnalisePropostaRequest(proposta));
            logger.info("Resposta positiva para a proposta {}", proposta.getId());
            
            Assert.isTrue(response.getResultadoSolicitacao().equals(RetornoAnalise.SEM_RESTRICAO), "O resultado deveria ser SEM_RESTRICAO");
            
            status = StatusAnalisaProposta.ELEGIVEL;
        } catch (FeignException.UnprocessableEntity e){
        	
            logger.warn("Resposta negativa. Feign {}",  e.getMessage());
            
            status = StatusAnalisaProposta.NAO_ELEGIVEL;
        }
        proposta.setStatusRestricao(status);
    }
}
