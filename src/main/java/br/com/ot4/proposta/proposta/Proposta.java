package br.com.ot4.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.cartao.Cartao;
import br.com.ot4.proposta.cartao.SolicitacaoCartaoRequest;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String documento;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
    private StatusAnalisaProposta statusRestricao;
	
	@JoinColumn(name = "cartao_id")
    @OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;
	
	@Deprecated
	public Proposta () {}

	public Proposta(Long id, @NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		super();
		this.id = id;
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public StatusAnalisaProposta getStatusRestricao() {
		return statusRestricao;
	}

	public void setStatusRestricao(StatusAnalisaProposta statusRestricao) {
		this.statusRestricao = statusRestricao;
	}

	public Long getId() {
		return id;
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

	public Cartao getCartao() {
	    return cartao;
	  }

	  public void setCartao(Cartao cartao) {
	    this.cartao = cartao;
	  }
	  
	  public SolicitacaoCartaoRequest toModelCartao() {
			return new SolicitacaoCartaoRequest(documento, nome, id.toString());
		}

	  public void adicionaCartao(Cartao cartao){
			this.cartao = cartao;
		}
	
}
