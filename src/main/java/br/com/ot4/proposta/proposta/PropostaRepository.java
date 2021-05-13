package br.com.ot4.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ot4.proposta.cartao.Cartao;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);
	
	boolean existsByDocumento(String documento);

	@Query(value = "SELECT * FROM proposta WHERE proposta.cartao_id IS NULL AND proposta.status_restricao = 'ELEGIVEL'", nativeQuery = true)
    List<Proposta> findAllWhereCartaoIdIsNull();
	
	List<Proposta> findByStatusRestricaoAndCartao(StatusAnalisaProposta statusRestricao, Cartao cartao);


}
