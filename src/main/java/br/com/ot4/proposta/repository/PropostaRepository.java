package br.com.ot4.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ot4.proposta.proposta.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

}
