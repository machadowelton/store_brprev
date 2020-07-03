package br.com.store_brprev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.store_brprev.domain.dto.ProductDTO;
import br.com.store_brprev.domain.entities.Product;
import br.com.store_brprev.domain.exceptions.ProductNotFoundException;
import br.com.store_brprev.services.impl.ProductServiceImpl;
import br.com.store_brprev.services.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {
	
	
	@MockBean
	private ProductRepository productRepository;
	
	private IProductService productService;
	
	@BeforeEach
	public void setUp() {
		this.productService = new ProductServiceImpl(productRepository);
	}
	
	ProductDTO productDTONew = ProductDTO
									.builder()
									.name("Milho")
									.description("Lata de milho")
									.value(BigDecimal.valueOf(10d))
									.sku("14545548787")
									.build();
	ProductDTO productDTOSaved = ProductDTO
									.builder()
									.id(1l)
									.name("Milho")
									.description("Lata de milho")
									.value(BigDecimal.valueOf(10d))
									.sku("14545548787")
									.build();
	Product productEntityNew = Product
								.builder()								
								.name("Milho")
								.description("Lata de milho")
								.value(BigDecimal.valueOf(10d))
								.sku("14545548787")
								.build();
	
	Product productEntitySaved = Product
									.builder()
									.id(1l)
									.name("Milho")
									.description("Lata de milho")
									.value(BigDecimal.valueOf(10d))
									.sku("14545548787")
									.build();
	
	@Test
	@DisplayName("Deve retornar um produto pelo id")
	public void findByIdTest() {
		Long id = 1l;
		Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(productEntitySaved));
		ProductDTO productDtoFound = productService.findById(id);
		assertThat(productDtoFound.equals(productDTOSaved)).isTrue();
	}
	
	@Test
	@DisplayName("Deve retornar erro ProductNotFoundException")
	public void findByIdProductNotFoundTest() {
		Long id = 1l;
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Throwable thr = catchThrowable(() -> {
			productService.findById(id);
		});
		assertThat(thr).isInstanceOf(ProductNotFoundException.class);
		assertThat(thr.getMessage().equals("No product found by id: " + id));
	}
}
