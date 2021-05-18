package br.com.ot4.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.cartao.Cartao;

public class ViagemRequest {
	
	@NotBlank
    private String destinoViagem;

    @NotNull
    @Future
    private LocalDate dataTermino;

    private String ipClient;

    private String userAgent;

    public ViagemRequest() {}
    
    public ViagemRequest(String destinoViagem, LocalDate dataTermino, String ipClient, String userAgent) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;

    }

    public Viagem converteViagemRequestParaAvisoViagem(Cartao cartao){
        return new Viagem(this.destinoViagem, this.dataTermino, this.ipClient, this.userAgent, cartao);
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
