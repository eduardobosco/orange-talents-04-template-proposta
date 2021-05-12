package br.com.ot4.proposta.proposta;

import java.math.BigDecimal;
import java.util.Optional;

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
	
	private String status;
	
	public PropostaResponse() {}
	
	

	public PropostaResponse(Long idProposta, @NotBlank String documento, @NotBlank @Email @Size(max = 128) String email,
			@NotBlank String nome, @NotBlank String endereco, @NotNull @Positive BigDecimal salario, String status) {
		super();
		this.idProposta = idProposta;
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
	}
	
	public PropostaResponse(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.salario = proposta.getSalario();
		this.endereco=proposta.getEndereco();
		this.status=proposta.getStatusRestricao().toString();
	}
	

	public PropostaResponse(Optional<Proposta> proposta) {
		this.idProposta = proposta.get().getId();
		this.documento = proposta.get().getDocumento();
		this.nome = proposta.get().getNome();
		this.email = proposta.get().getEmail();
		this.endereco = proposta.get().getEndereco();
		this.salario = proposta.get().getSalario();
		this.status = proposta.get().getStatusRestricao().toString();
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

	public String getStatus() {
		return status;
	}

	
}
