package br.com.store_brprev.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Audit implements Serializable {

	private static final long serialVersionUID = -2836077879147542131L;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	@LastModifiedDate
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
	
	
}