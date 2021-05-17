package br.com.ot4.proposta.bloqueio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ot4.proposta.cartao.Cartao;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {

	Optional<Bloqueio> findByCartaoAndStatus(Cartao possivelCartao, Boolean status);

}
