package br.com.ot4.proposta.cartao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.ot4.proposta.biometria.Biometria;
import br.com.ot4.proposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numeroCartao;

	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometrias;

	@Deprecated
	public Cartao() {
	}

	public Cartao(Proposta proposta) {
		this.proposta = proposta;
	}

	public Cartao(String id, Proposta proposta) {
		this.numeroCartao = id;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

}
