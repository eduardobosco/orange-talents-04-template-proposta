package br.com.ot4.proposta.bloqueio;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.CartaoClient;
import br.com.ot4.proposta.cartao.CartaoRepository;
import br.com.ot4.proposta.compartilhado.RequestHeadersUtil;
import feign.FeignException;

@RestController
public class BloqueioController {

    private final String APP_NAME = "proposta-api";

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private RequestHeadersUtil headersUtil;

    @PostMapping("/cartoes/{id}/bloqueio")
    public ResponseEntity<?> bloqueio(@PathVariable Long id,
                                      HttpServletRequest request) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = possivelCartao.get();
        try{
            cartaoClient.bloqueio(cartao.getNumeroCartao(), new BloqueioRequest(APP_NAME));
        }catch (FeignException.UnprocessableEntity ex) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("violacaoRegraDeNegocio", "O cartão já está bloqueado!");
            return ResponseEntity.unprocessableEntity().body(errors);
        }catch (FeignException e){
            Map<String, Object> errors = new HashMap<>();
            errors.put("violacaoRegraDeNegocio", "Não foi possivel proseguir com a solicitação!");
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        cartao.bloquear();
        Map<String, String> headers = headersUtil.getRequestHeaders(request);
        BloqueioCartao bloqueio = new BloqueioCartao(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
        bloqueioRepository.save(bloqueio);

        return ResponseEntity.ok().build();
    }

}
