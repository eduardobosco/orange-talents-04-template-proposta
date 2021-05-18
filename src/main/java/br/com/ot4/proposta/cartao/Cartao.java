package br.com.ot4.proposta.cartao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.ot4.proposta.biometria.Biometria;
import br.com.ot4.proposta.bloqueio.BloqueioCartao;
import br.com.ot4.proposta.proposta.Proposta;
import br.com.ot4.proposta.viagem.Viagem;

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
	
	@Enumerated(EnumType.STRING)
    private StatusCartao status = StatusCartao.DESBLOQUEADO;
	
	@OneToMany(mappedBy = "cartao")
    private List<BloqueioCartao> bloqueios;
	
	@OneToMany(mappedBy = "cartao")
    private List<Viagem> avisosDeViagem;

	@Deprecated
	public Cartao() {
	}
	
	public void bloquear() {
        status = StatusCartao.BLOQUEADO;
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
