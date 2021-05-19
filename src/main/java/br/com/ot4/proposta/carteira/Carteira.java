package br.com.ot4.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira tipoCarteira;

    @Deprecated
    public Carteira() {
    }

    public Carteira(@NotNull Cartao cartao, @NotBlank String email, @NotNull TipoCarteira tipoCarteira) {
        this.cartao = cartao;
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public Long getId() {
        return id;
    }

	public Cartao getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}
    
    
}
