package br.com.store_brprev.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import br.com.store_brprev.domain.enums.EOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Audit {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO, 
	    generator="native"
	)
	@GenericGenerator(
	    name = "native", 
	    strategy = "native"
	)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;
	
	@ManyToMany
	@JoinTable(
			name = "orders_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id")
			)
	@Builder.Default
	private Set<Product> products = new HashSet<Product>();
	
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private EOrderStatus status = EOrderStatus.OPEN;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date orderStartedIn = new Date();
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderClosedIn;
	
	@Column(scale = 2)
	@Builder.Default
	private BigDecimal totalOrderValue = new BigDecimal(0.0d);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public EOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EOrderStatus status) {
		this.status = status;
	}

	public Date getOrderStartedIn() {
		return orderStartedIn;
	}

	public void setOrderStartedIn(Date orderStartedIn) {
		this.orderStartedIn = orderStartedIn;
	}

	public Date getOrderClosedIn() {
		return orderClosedIn;
	}

	public void setOrderClosedIn(Date orderClosedIn) {
		this.orderClosedIn = orderClosedIn;
	}

	public BigDecimal getTotalOrderValue() {
		return totalOrderValue;
	}

	public void setTotalOrderValue(BigDecimal totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}

	
}
