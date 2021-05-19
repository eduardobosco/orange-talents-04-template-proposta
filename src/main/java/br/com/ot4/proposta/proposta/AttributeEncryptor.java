package br.com.ot4.proposta.proposta;

import javax.persistence.AttributeConverter;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
	public class AttributeEncryptor implements AttributeConverter<String, String> {

	    @Override
	    public String convertToDatabaseColumn(String attribute) {
	        try {
	            return Encryptors.text("123456", "123456").encrypt(attribute);
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }

	    @Override
	    public String convertToEntityAttribute(String dbData) {
	        try {
	            return Encryptors.text("123456", "123456").decrypt(dbData);
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }

}
