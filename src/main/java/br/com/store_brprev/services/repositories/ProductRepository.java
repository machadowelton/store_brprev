package br.com.store_brprev.services.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.store_brprev.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	boolean existsBySku(String sku);
	
	Page<Product> findByAvailableIsTrue(Pageable pageable);
	
}
