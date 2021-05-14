package br.com.ot4.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoRepository;

@RestController
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new BiometriaFingerPrintValidador());
    }

    @PostMapping("cartao/{idCartao}/biometria")
    public ResponseEntity<?> criar(@PathVariable("idCartao") Long idCartao, @RequestBody @Valid NovaBiometriaRequest novaBiometriaRequest, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if (possivelCartao.isEmpty()) {
        	logger.info("Esta faltando o numero do Cartao");
            return ResponseEntity.notFound().build();
        }
        logger.info("Iniciando o cadastro de uma nova Biometria" );
        
        Biometria biometria = novaBiometriaRequest.toModel(possivelCartao.get());
        biometriaRepository.save(biometria);
        URI location = uriBuilder.path("cartao/{idCartao}/biometria/{id}").buildAndExpand(idCartao, biometria.getId()).toUri();
        logger.info("Nova Biometria Criada" );
        return ResponseEntity.created(location).body(new BiometriaResponse(biometria));
    }
}
