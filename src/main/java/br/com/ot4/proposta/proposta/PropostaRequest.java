package br.com.ot4.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;



import br.com.ot4.proposta.compartilhado.CpfOuCnpj;
import br.com.ot4.proposta.compartilhado.UniqueValue;
import br.com.ot4.proposta.repository.PropostaRepository;

public class PropostaRequest {
	
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
	
	public PropostaRequest() {}

	public PropostaRequest(@NotBlank String documento, @NotBlank @Email @Size(max = 128) String email,
			@NotBlank String nome, @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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

	public Proposta converter(PropostaRepository propostaRepository) {
		return new Proposta(documento, email, nome, endereco, salario);
	}
	
	
}
