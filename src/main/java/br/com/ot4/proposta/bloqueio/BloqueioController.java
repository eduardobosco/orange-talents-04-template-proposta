package br.com.ot4.proposta.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot4.proposta.biometria.BiometriaController;
import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoClient;
import br.com.ot4.proposta.cartao.CartaoRepository;
import feign.FeignException;

@RestController("/bloqueio")
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioRepository bloqueioRepository;
    @Autowired
    private CartaoClient cartaoClient;
    
    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloquear(@RequestParam("idCartao") Long idCartao, HttpServletRequest httpServletRequest) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        
        if (possivelCartao.isEmpty()) {
        	logger.info("Cartão não Encontrado, verifique o numero digitado" );
            return ResponseEntity.notFound().build();
        }
        
        Optional<Bloqueio> possivelBloqueio = bloqueioRepository.findByCartaoAndStatus(possivelCartao.get(), true);
        if (possivelBloqueio.isPresent()) {
        	
            return ResponseEntity.unprocessableEntity().body("O cartão informado já se encontra como bloqueado no sistema");
        }
        
        Bloqueio bloqueio = new Bloqueio(httpServletRequest.getRemoteAddr(), httpServletRequest.getHeader("User-Agent"), possivelCartao.get());
        
        
        try {
            String statusBloqueio = cartaoClient.bloqueiaCartao(possivelCartao.get().getNumeroCartao(), new NovoBloqueioRequest("Sistema de propostas"));
            logger.info("Bloqueando Cartao" );
            verificaESetaStatus(statusBloqueio, bloqueio);
            
        } catch (FeignException.UnprocessableEntity e) {
       
            bloqueioRepository.save(bloqueio);            
            return ResponseEntity.badRequest().build();
        }
        
        bloqueioRepository.save(bloqueio);
        logger.info("Cartão {} foi bloqueado", possivelCartao.get().getNumeroCartao());
        return ResponseEntity.ok().build();
    }

    private void verificaESetaStatus(String status, Bloqueio bloqueio) {
        {
            bloqueio.setStatus(true);
        }
    }
}
