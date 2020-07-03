package br.com.store_brprev.services.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.store_brprev.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByLogin(String login);
	
	Optional<User> findByLogin(String login);
	
}
