package br.com.store_brprev.domain.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.store_brprev.domain.enums.EOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(
		value = { "status", "orderStartedIn", "orderClosedIn", "audit", "audit" },
		allowGetters = true
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private Long id;
	
	@JsonProperty("client")
	private ClientDTO client;
	
	@JsonInclude(Include.NON_EMPTY)
	@JsonProperty("products")
	@Builder.Default
	private Set<ProductDTO> products = new HashSet<ProductDTO>();
	
	@Builder.Default
	private EOrderStatus status = EOrderStatus.OPEN;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date orderStartedIn = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderClosedIn;
	
	@Column(scale = 2)
	@Builder.Default
	private BigDecimal totalOrderValue = new BigDecimal(0d);
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("audit")
	private AuditDTO audit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderClosedIn == null) ? 0 : orderClosedIn.hashCode());
		result = prime * result + ((orderStartedIn == null) ? 0 : orderStartedIn.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrderDTO other = (OrderDTO) obj;
		if (audit == null) {
			if (other.audit != null)
				return false;
		} else if (!audit.equals(other.audit))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderClosedIn == null) {
			if (other.orderClosedIn != null)
				return false;
		} else if (!orderClosedIn.equals(other.orderClosedIn))
			return false;
		if (orderStartedIn == null) {
			if (other.orderStartedIn != null)
				return false;
		} else if (!orderStartedIn.equals(other.orderStartedIn))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDTO("
					+ "id=" + id 
					+ ", client=" + client 
					+ (!products.isEmpty() ? ", products=" + products : "")  
					+ ", status=" + status
					+ ", orderStartedIn=" + orderStartedIn 
					+ orderClosedIn != null ? ", orderClosedIn=" + orderClosedIn : "" 
					+ ", audit=" + audit
					+ ")";
	}
	
			
}
