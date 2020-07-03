package br.com.store_brprev.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.store_brprev.domain.dto.ClientDTO;
import br.com.store_brprev.domain.dto.OrderDTO;
import br.com.store_brprev.domain.entities.Order;
import br.com.store_brprev.domain.entities.Product;
import br.com.store_brprev.domain.enums.EOrderStatus;
import br.com.store_brprev.domain.exceptions.DuplicateProductOnOrderException;
import br.com.store_brprev.domain.exceptions.NoProductsOnOrderException;
import br.com.store_brprev.domain.exceptions.OrderClosedException;
import br.com.store_brprev.domain.exceptions.OrderNotFoundException;
import br.com.store_brprev.domain.exceptions.ProductNotAvaliableException;
import br.com.store_brprev.services.IOrderService;
import br.com.store_brprev.services.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	private final OrderRepository orderRepository;
	private final ProductServiceImpl productService;
	
	private final ClientServiceImpl clientService;
	
	private final ModelMapper mapper = new ModelMapper();
	
	TypeMap<Order, OrderDTO> typeMapEntityToDto = 
			mapper.createTypeMap(Order.class, OrderDTO.class)
				.addMapping((s) -> s.getCreatedAt(), (OrderDTO d, Date v) -> d.getAudit().setCreatedAt(v))
				.addMapping((s) -> s.getUpdatedAt(), (OrderDTO d, Date v) -> d.getAudit().setUpdatedAt(v));
	
	TypeMap<OrderDTO, Order> typeMapDtoToEntity = 
			mapper.createTypeMap(OrderDTO.class, Order.class)
				.addMapping((s) -> s.getAudit().getCreatedAt(), (Order d, Date v) -> d.setCreatedAt(v))
				.addMapping((s) -> s.getAudit().getUpdatedAt(), (Order d, Date v) -> d.setUpdatedAt(v));
	
	public OrderServiceImpl(final OrderRepository orderRepository, final ClientServiceImpl clientService, final ProductServiceImpl productService) {
		this.orderRepository = orderRepository;
		this.clientService = clientService;
		this.productService = productService;
	}

	@Override
	public OrderDTO findByIdAndClientId(Long id, Long clientId) {
		return orderRepository.findByIdAndClientId(id, clientId)
					.map((m) -> mapper.map(m, OrderDTO.class))
					.orElseThrow(() -> new OrderNotFoundException("No found order with id: " + id + " and client id: " + id));
	}

	@Override
	public Page<OrderDTO> findByClientId(Long clientId, Pageable pageable) {
		return orderRepository.findByClientId(clientId, pageable)
					.map((m) -> mapper.map(m, OrderDTO.class));
	}

	@Override
	public OrderDTO openOrder(Long clientId) {
		ClientDTO client = clientService.findById(clientId);
		OrderDTO order = OrderDTO.builder()
								.client(client)
								.build();
		Order orderEntity = mapper.map(order, Order.class);
		return mapper.map(orderRepository.save(orderEntity), OrderDTO.class);
	}

	@Override
	public OrderDTO closeOrderByIdAndClientId(Long id, Long clientId) {
		Order order = orderRepository.findByIdAndClientId(id, clientId)
						.map((m) -> {
							if(m.getStatus() == EOrderStatus.CLOSED)
								throw new OrderClosedException("The order already closed");
							if(m.getProducts().isEmpty())
								throw new NoProductsOnOrderException("The order cannot be closed because have no products in this order");
							return m;
						})
						.orElseThrow(() -> new OrderNotFoundException("No found order with id: " + id + " and client id: " + id));
		final BigDecimal sumValueProducts = order.getProducts().stream()
											.map((v) -> v.getValue())
											.reduce(new BigDecimal(0.0d), (a, v) -> a.add(v));
		Order orderToClose = Order.builder()
								.id(order.getId())
								.orderStartedIn(order.getOrderStartedIn())
								.products(order.getProducts())
								.client(order.getClient())
								.totalOrderValue(sumValueProducts)
								.status(EOrderStatus.CLOSED)
								.build();
		
		return mapper.map(orderToClose, OrderDTO.class);
	}

	@Override
	public OrderDTO addProductToOrder(Long id, Long clientId, Long productId) {
		Order order = orderRepository.findByIdAndClientId(id, clientId)
							.map((m) -> {
								if(m.getStatus() == EOrderStatus.CLOSED)
									throw new OrderClosedException("The order already closed");
								return m;
							})
							.orElseThrow(() -> new OrderNotFoundException("No found order with id: " + id + " and client id: " + id));
		Product product = mapper.map(productService.findById(productId), Product.class);
		if(!product.getAvailable())
			throw new ProductNotAvaliableException("This product already was sold and cannot be sale again. Product id: " + product.getId());
		if(!order.getProducts().isEmpty())
			if(order.getProducts().contains(product))
				throw new DuplicateProductOnOrderException("The product of id: " + productId + " is already in the order of id:" +  order.getId());
		order.getProducts().add(product);
		return mapper.map(orderRepository.save(order), OrderDTO.class);
	}

	@Override
	public OrderDTO addProductsToOrder(Long id, Long clientId, List<Long> productsIds) {
		Order order = orderRepository.findByIdAndClientId(id, clientId)
				.map((m) -> {
					if(m.getStatus() == EOrderStatus.CLOSED)
						throw new OrderClosedException("The order already closed");
					return m;
				})
				.orElseThrow(() -> new OrderNotFoundException("No found order with id: " + id + " and client id: " + id));
		List<Product> products = 
						productsIds.stream()
								.map(productService::findById)
								.map((m) -> mapper.map(m, Product.class))
								.collect(Collectors.toList());
		String idProductsNoAvailable = products.stream()
												.filter((f) -> !f.getAvailable())
												.map((m) -> m.getId().toString())
												.reduce("", (a, productId) -> a.concat(", " + productId))
												.toString();
		if(idProductsNoAvailable.length() > 0)
			throw new ProductNotAvaliableException("The products with id's " + idProductsNoAvailable.replaceAll(", $", "")  +  "have already been sold: ");
		order.getProducts().addAll(products);		
		return mapper.map(orderRepository.save(order), OrderDTO.class);
	}

}
