package br.com.ot4.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.ot4.proposta.cartao.Cartao;

@Entity
@Table(name = "bloqueios")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @CreationTimestamp
    private LocalDateTime instante;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(@NotNull Cartao cartao, @NotBlank String ip, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.ip = ip;
        this.userAgent = userAgent;
    }
}