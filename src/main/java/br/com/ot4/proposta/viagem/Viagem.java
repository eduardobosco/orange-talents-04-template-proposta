package br.com.ot4.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
public class Viagem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinoViagem;
    private LocalDate dataTermino;
    private String ipClient;
    private String userAgent;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Viagem() {
    }

    public Viagem(String destinoViagem, LocalDate dataTermino, String ipClient, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
