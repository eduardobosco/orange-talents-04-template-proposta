package br.com.ot4.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.ot4.proposta.compartilhado.CpfOuCnpj;
import br.com.ot4.proposta.compartilhado.UniqueValue;

public class PropostaResponse {
	
	private Long idProposta;
	
	@NotBlank @CpfOuCnpj
	private String documento;
	
	@NotBlank @Email @Size(max = 128)
	@UniqueValue(domainClass = Proposta.class, fieldName = "email")
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@NotNull @Positive
	private BigDecimal salario;
	
	public PropostaResponse() {}

	public PropostaResponse(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.salario = proposta.getSalario();
		this.endereco=proposta.getEndereco();
	}
	

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

}
