package br.com.store_brprev.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.store_brprev.domain.dto.ProductDTO;
import br.com.store_brprev.services.IProductService;
import br.com.store_brprev.services.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	private final IProductService service;
	
	public ProductRestController(final ProductServiceImpl productService) {
		this.service = productService;
	}
	
	@GetMapping("/{id}")
	public ProductDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public Page<ProductDTO> find(Pageable pageable) {
		return service.find(pageable);
	}
	
	@GetMapping("/available")
	public Page<ProductDTO> findOnlyAvailable(Pageable pageable) {
		return service.findOnlyAvaliables(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO save(@RequestBody @Valid ProductDTO product) {
		return service.save(product);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ProductDTO update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO product) {
		return service.update(id, product);
	}
	
}
