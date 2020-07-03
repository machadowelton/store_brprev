package br.com.store_brprev.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.store_brprev.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByCpfOrEmail(String cpf, String email);
	
}
