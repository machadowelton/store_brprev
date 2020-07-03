package br.com.store_brprev.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.store_brprev.domain.dto.ProductDTO;

public interface IProductService {
	
	ProductDTO findById(Long id);
	
	Page<ProductDTO> find(Pageable pageable);
	
	Page<ProductDTO> findOnlyAvaliables(Pageable pageable);
	
	ProductDTO save(ProductDTO product);
	
	ProductDTO update(Long id, ProductDTO product);
	
}
