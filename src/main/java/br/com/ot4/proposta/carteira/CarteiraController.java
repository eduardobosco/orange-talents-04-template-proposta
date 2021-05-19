package br.com.ot4.proposta.carteira;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoClient;
import br.com.ot4.proposta.cartao.CartaoRepository;
import feign.FeignException;

@RestController
public class CarteiraController {

	@Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/cartoes/{id}/carteiras")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody CarteiraRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = possivelCartao.get();

        Map<String, Object> errors = new HashMap<>();
        try {
            Optional<Carteira> possivelCarteira = carteiraRepository.findByTipoCarteiraAndCartao(request.getCarteira(), cartao);
            if(possivelCarteira.isPresent()){
                errors.put("violacaoRegraDeNegocio", "O cartão já está cadastrado para essa carteira!");
                return ResponseEntity.badRequest().body(errors);
            }

            cartaoClient.adiciona(cartao.getNumeroCartao(), new CarteiraClientRequest(request));

            Carteira carteira = carteiraRepository.save(new Carteira(cartao, request.getEmail(), request.getCarteira()));
            URI location = uriBuilder.path("/cartoes/{id}/carteiras/{id}").build(cartao.getId(), carteira.getId());

            return ResponseEntity.created(location).body(new CarteiraResponse(carteira));
            
        }catch (FeignException.FeignClientException ex){
            errors.put("violacaoRegraDeNegocio", "Não foi possível realizar a operação!");
            return ResponseEntity.unprocessableEntity().body(errors);
        }

    }

}
