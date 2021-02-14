package br.com.zup.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	Optional<Proposta> findByDocumento(String documento);
	
	boolean existsByDocumento(String documento);
	@Query("SELECT p FROM Proposta p WHERE p.status = :status AND p.cartao is null ")
	List<Proposta> listaPropostaSemCartao(Status status);//, Pageable paginacao
}
