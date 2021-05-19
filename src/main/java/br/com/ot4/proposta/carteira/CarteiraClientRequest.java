package br.com.ot4.proposta.carteira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarteiraClientRequest {

	@JsonProperty
    private String email;

    @JsonProperty
    private String carteira;

    public CarteiraClientRequest(CarteiraRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getCarteira().toString();
    }
}
