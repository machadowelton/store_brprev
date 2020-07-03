package br.com.store_brprev.domain.dto;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(
		value = { "available", "audit", "audit" },
		allowGetters = true
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long id;
	
	@NotNull(message = "The product's name is mandatory")
	private String name;
	
	private String description;
	
	@NotNull(message = "The sku code is mandatory")
	private String sku;
	
	@NotNull(message = "The product's value is mandatory")
	private BigDecimal value;
	
	@JsonInclude(Include.NON_EMPTY)
	@JsonProperty("orders")
	@Builder.Default
	private Set<OrderDTO> orders = new HashSet<OrderDTO>();
	
	@Builder.Default
	private Boolean available = Boolean.TRUE;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("audit")
	private AuditDTO audit;

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

	public Set<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public AuditDTO getAudit() {
		return audit;
	}

	public void setAudit(AuditDTO audit) {
		this.audit = audit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audit == null) ? 0 : audit.hashCode());
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (audit == null) {
			if (other.audit != null)
				return false;
		} else if (!audit.equals(other.audit))
			return false;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductDTO("
					+ "id=" + id 
					+ ", name=" + name 
					+ ", description=" + description 
					+ ", sku=" + sku 
					+ ", value=" + value 
					+ (!orders.isEmpty() ? ", orders=" + orders : "") 
					+ ", available=" + available 
					+ ", audit=" + audit 
					+ ")";
	}
	
}
