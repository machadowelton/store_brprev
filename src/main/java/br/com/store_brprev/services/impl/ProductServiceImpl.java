package br.com.store_brprev.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.store_brprev.domain.dto.AuditDTO;
import br.com.store_brprev.domain.dto.ProductDTO;
import br.com.store_brprev.domain.entities.Product;
import br.com.store_brprev.domain.exceptions.ProductNotAvaliableException;
import br.com.store_brprev.domain.exceptions.ProductNotFoundException;
import br.com.store_brprev.domain.exceptions.UniqueDataViolationException;
import br.com.store_brprev.services.IProductService;
import br.com.store_brprev.services.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	private final ModelMapper mapper = new ModelMapper();
	TypeMap<Product, ProductDTO> typeMapEntityToDto = 
			mapper.createTypeMap(Product.class, ProductDTO.class)
				.addMapping((s) -> s.getCreatedAt(), (ProductDTO d, Date v) -> d.getAudit().setCreatedAt(v))
				.addMapping((s) -> s.getUpdatedAt(), (ProductDTO d, Date v) -> d.getAudit().setUpdatedAt(v));
	
	TypeMap<ProductDTO, Product> typeMapDtoToEntity = 
			mapper.createTypeMap(ProductDTO.class, Product.class)
				.addMapping((s) -> s.getAudit().getCreatedAt(), (Product d, Date v) -> d.setCreatedAt(v))
				.addMapping((s) -> s.getAudit().getUpdatedAt(), (Product d, Date v) -> d.setUpdatedAt(v));

	@Override
	public ProductDTO findById(Long id) {
		return productRepository.findById(id)
					.map((p) -> mapper.map(p, ProductDTO.class))
					.orElseThrow(() -> new ProductNotFoundException("No product found by id: " + id));
					
	}

	@Override
	public Page<ProductDTO> find(Pageable pageable) {
		return productRepository
					.findAll(pageable)
					.map((p) -> mapper.map(p, ProductDTO.class));
	}

	@Override
	public Page<ProductDTO> findOnlyAvaliables(Pageable pageable) {
		return productRepository
				.findByAvailableIsTrue(pageable)
				.map((p) -> mapper.map(p, ProductDTO.class));
	}

	@Override
	public ProductDTO save(ProductDTO product) {
		if(productRepository.existsBySku(product.getSku()))
			throw new UniqueDataViolationException("Already a product with sku: " + product.getSku());
		Product productEntity = mapper.map(product, Product.class);
		return mapper.map(productRepository.save(productEntity), ProductDTO.class); 
	}

	@Override
	public ProductDTO update(Long id, ProductDTO product) {
		Product productExists = productRepository.findById(id)
							.map((m) -> {
								if(m.getAvailable()) return m;
								throw new ProductNotAvaliableException("This product was sold and cannot be updated");
							})
							.orElseThrow(() -> new ProductNotFoundException("No product found by id: " + id));
		product.setId(id);
		product.setAudit(AuditDTO.builder().createdAt(productExists.getCreatedAt()).build());
		Product productEntity = mapper.map(product, Product.class);
		return mapper.map(productRepository.save(productEntity), ProductDTO.class);
	}

}
