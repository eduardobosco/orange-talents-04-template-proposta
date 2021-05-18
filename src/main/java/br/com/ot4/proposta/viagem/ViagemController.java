package br.com.ot4.proposta.viagem;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoRepository;

@RestController("/viagens")
public class ViagemController {
  
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private ViagemRepository viagemRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@RequestBody @Valid ViagemRequest avisoViagemRequest, @PathVariable Long idCartao, @RequestHeader(value = "User-Agent") @NotBlank String userAgent, @RequestHeader(value = "Host") @NotBlank String ipClient ){
        Optional<Cartao> cartaoPossivel = cartaoRepository.findById(idCartao);
        
        if(cartaoPossivel.isPresent()){
            avisoViagemRequest.setIpClient(ipClient);
            avisoViagemRequest.setUserAgent(userAgent);
            Viagem avisoViagem = avisoViagemRequest.converteViagemRequestParaAvisoViagem(cartaoPossivel.get());
            viagemRepository.save(avisoViagem);
            return ResponseEntity.ok(new ViagemResponse(avisoViagem));
        }
        return ResponseEntity.notFound().build();
    }
}
