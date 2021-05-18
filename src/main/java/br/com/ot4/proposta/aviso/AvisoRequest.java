package br.com.ot4.proposta.aviso;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisoRequest {
	
	@NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validoAte;
    
    public AvisoRequest() {}

    public AvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    @Override
    public String toString() {
        return "NovoAvisoRequest{" +
                "destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                '}';
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}