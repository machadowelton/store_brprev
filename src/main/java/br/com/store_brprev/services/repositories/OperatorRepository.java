package br.com.store_brprev.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.store_brprev.domain.entities.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
	
	
	List<Operator> findByCpfAndEmail(String cpf, String email);
	
}
