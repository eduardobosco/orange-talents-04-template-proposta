package br.com.ot4.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ot4.proposta.proposta.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

}
