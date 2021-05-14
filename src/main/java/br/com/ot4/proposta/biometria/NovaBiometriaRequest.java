package br.com.ot4.proposta.biometria;

import javax.validation.constraints.NotBlank;

import br.com.ot4.proposta.cartao.Cartao;

public class NovaBiometriaRequest {

    @NotBlank
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerprint, cartao);
    }
}
