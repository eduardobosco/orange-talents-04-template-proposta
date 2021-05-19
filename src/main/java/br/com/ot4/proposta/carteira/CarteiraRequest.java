package br.com.ot4.proposta.carteira;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

	@NotBlank
    private String email;

    @NotNull
    @NotBlank
    private TipoCarteira carteira;
    
    public CarteiraRequest() {}

    public CarteiraRequest(@NotBlank String email, @NotBlank TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
