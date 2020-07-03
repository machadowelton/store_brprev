package br.com.store_brprev.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.store_brprev.controllers.ProductRestController;
import br.com.store_brprev.domain.dto.ProductDTO;
import br.com.store_brprev.security.JWTManager;
import br.com.store_brprev.security.JwtRequestFilter;
import br.com.store_brprev.services.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductRestController.class)
@AutoConfigureMockMvc
public class ProductRestControllerTest {
	
	
	final String PRODUCTS_API = "/products";
	
	@MockBean
	JWTManager jwtManager;
	
	@MockBean
	JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductServiceImpl productService;
	
	ProductDTO productDTOSaved = ProductDTO
			.builder()
			.id(1l)
			.name("Milho")
			.description("Lata de milho")
			.value(BigDecimal.valueOf(10d))
			.sku("14545548787")
			.build();
	
	@Test
	@DisplayName("Deve retornar um produto pelo id")
	public void findByIdTest() throws Exception {
		Long id = 1l;
		BDDMockito.given(productService.findById(id)).willReturn(productDTOSaved);
		MockHttpServletRequestBuilder request = 
						MockMvcRequestBuilders
							.get(PRODUCTS_API + "/" + id)
							.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(id))
				.andExpect(jsonPath("name").value(productDTOSaved.getName()))
				.andExpect(jsonPath("description").value(productDTOSaved.getDescription()))
				.andExpect(jsonPath("value").value(productDTOSaved.getValue()))
				.andExpect(jsonPath("sku").value(productDTOSaved.getSku()))
				.andExpect(jsonPath("available").value(productDTOSaved.getAvailable()));
	}
	
}
