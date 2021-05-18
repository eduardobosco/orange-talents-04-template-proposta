package br.com.ot4.proposta.aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
@Table(name = "avisos")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    private String destino;

    private LocalDate validoAte;

    private String ip;

    private String userAgent;

    @CreationTimestamp
    private LocalDateTime instante;

    @Deprecated
    public Aviso() {
    }

    public Aviso(Cartao cartao, String destino, LocalDate validoAte, String ip, String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.validoAte = validoAte;
        this.ip = ip;
        this.userAgent = userAgent;
    }

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
    
    
}
