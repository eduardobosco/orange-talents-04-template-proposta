package br.com.ot4.proposta.aviso;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoClientRequest {
	
	@NotBlank
    @JsonProperty
    private String destino;

    @Future
    @NotNull
    @JsonProperty
    private LocalDate validoAte;

    public AvisoClientRequest(AvisoRequest request) {
        this.destino = request.getDestino();
        this.validoAte = request.getValidoAte();
    }
}