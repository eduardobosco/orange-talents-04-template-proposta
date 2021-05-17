package br.com.ot4.proposta.bloqueio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
public class Bloqueio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate bloqueadoEm;
    @NotNull
    private String ip;
    @NotNull
    private String userAgent;
    @NotNull
    private Boolean status;
    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {

    }

    public Bloqueio(@NotNull String ip, @NotNull String userAgent, Cartao cartao) {
        this.bloqueadoEm = LocalDate.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.status = false;
        this.cartao = cartao;
    }

    public LocalDate getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Boolean getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}