package br.com.ot4.proposta.biometria;

import java.time.LocalDateTime;

public class BiometriaResponse {
	
	private Long id;
    private LocalDateTime dataDeCriacao;
    private String fingerprint;

    public BiometriaResponse(Biometria biometria) {
        this.id = biometria.getId();
        this.dataDeCriacao = biometria.getDataDeCriacao();
        this.fingerprint = biometria.getFingerprint();
    }

    public Long getId() {
        return id;
    }

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public String getFingerprint() {
		return fingerprint;
	}

    

}
