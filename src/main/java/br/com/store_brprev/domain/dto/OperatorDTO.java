package br.com.store_brprev.domain.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(
		value = {"audit", "auditDTO"},
		allowGetters = true
)
@Builder
public class OperatorDTO {
	
	private Long id;
	
	@NotNull(message = "The first name is mandatory")
	private String firstName;
	
	@NotNull(message = "The last name is mandatory")
	private String lastName;
	
	@NotNull(message = "The cpf document is mandatory")
	@Pattern(regexp = "\\d{11}", message = "the cpf document does not matches with pattern {00000000000}")
	private String cpf;
	
	@Email(message = "The value not matches with email compliance")
	@NotNull(message = "The email is mandatory")
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user")
	private UserDTO user;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("audit")
	private AuditDTO audit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		OperatorDTO other = (OperatorDTO) obj;
		if (audit == null) {
			if (other.audit != null)
				return false;
		} else if (!audit.equals(other.audit))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperatorDTO("
					+ "id=" + id 
					+ ", firstName=" + firstName 
					+ ", lastName=" + lastName 
					+ ", cpf=" + cpf
					+ ", email=" + email 
					+ user != null ? ", user=" + user : "" 
					+ ", auditDTO=" + audit 
					+ ")";
	}
	
	
}
