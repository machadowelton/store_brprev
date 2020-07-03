package br.com.store_brprev.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.store_brprev.domain.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	Optional<Order> findByIdAndClientId(Long id, Long clientId);

	Page<Order> findByClientId(Long clientId, Pageable pageable);
	
}
