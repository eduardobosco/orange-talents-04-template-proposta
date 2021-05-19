package br.com.ot4.proposta.carteira;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ot4.proposta.cartao.Cartao;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

	Optional<Carteira> findByTipoCarteiraAndCartao(TipoCarteira carteira, Cartao cartao);

}
