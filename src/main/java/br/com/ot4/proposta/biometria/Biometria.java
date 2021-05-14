package br.com.ot4.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fingerprint;

    @ManyToOne
    private Cartao cartao;
    
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    public Biometria() {
    }

	public Biometria(String fingerprint, Cartao cartao, LocalDateTime dataDeCriacao) {
		super();
		this.fingerprint = fingerprint;
		this.cartao = cartao;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
		super();
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

	public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Cartao getCartao() {
        return cartao;
    }
    
    public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}