package br.com.store_brprev.domain.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Audit{

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
	
	@NotNull(message = "The product's name is mandatory")
	private String name;
	
	private String description;
	
	@NotNull(message = "The sku code is mandatory")
	@Column(unique = true)
	private String sku;
	
	@NotNull(message = "The product's value is mandatory")
	@Column(scale = 2)
	private BigDecimal value;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
	@Builder.Default
	private Set<Order> orders = new HashSet<Order>();
	
	@Builder.Default
	private Boolean available = Boolean.TRUE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}			
		
}
