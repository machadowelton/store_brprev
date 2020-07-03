package br.com.store_brprev.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {
	
	private Date createdAt;
	
	private Date updatedAt;	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		AuditDTO other = (AuditDTO) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuditDTO(createdAt=" + createdAt + ", updatedAt=" + updatedAt + ")";
	}		
}
