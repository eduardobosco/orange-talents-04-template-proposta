package br.com.ot4.proposta.aviso;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoClient;
import br.com.ot4.proposta.cartao.CartaoRepository;
import br.com.ot4.proposta.compartilhado.RequestHeadersUtil;
import feign.FeignException;

@RestController
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient client;

    @Autowired
    private RequestHeadersUtil headersUtil;

    @PostMapping("/cartoes/{id}/avisos")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody @Valid AvisoRequest avisoRequest,
                                      HttpServletRequest request) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();
        try {
            client.avisa(cartao.getNumeroCartao(), new AvisoClientRequest(avisoRequest));
            cartao.associarAviso(avisoRequest, headersUtil.getRequestHeaders(request));
            cartaoRepository.save(cartao);
        }catch (FeignException ex){
            System.out.println(ex);
            Map<String, Object> errors = new HashMap<>();
            errors.put("violacaoRegraDeNegocio", "Não foi possível realizar o aviso!");
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().build();
    }

}
