package br.com.store_brprev.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.store_brprev.domain.dto.OrderDTO;

public interface IOrderService {
	
	OrderDTO findByIdAndClientId(Long id, Long clientId);
	
	Page<OrderDTO> findByClientId(Long clientId, Pageable pageable);
	
	OrderDTO openOrder(Long clientId);
	
	OrderDTO closeOrderByIdAndClientId(Long id, Long clientId);
	
	OrderDTO addProductToOrder(Long id, Long clientId,Long productId);
	
	OrderDTO addProductsToOrder(Long id, Long clientId, List<Long> productsIds);
	
}
