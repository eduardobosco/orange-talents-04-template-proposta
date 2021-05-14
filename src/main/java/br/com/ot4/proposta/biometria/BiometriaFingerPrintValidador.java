package br.com.ot4.proposta.biometria;

import java.util.Base64;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BiometriaFingerPrintValidador implements Validator {
	
	@Override
    public boolean supports(Class<?> clazz) {
        return NovaBiometriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovaBiometriaRequest biometriaRequest = (NovaBiometriaRequest) target;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            decoder.decode(biometriaRequest.getFingerprint());
        } catch(IllegalArgumentException iae) {
            errors.rejectValue("fingerprint", null, "Fingerprint não foi passada em Base64");
        }
    }
}