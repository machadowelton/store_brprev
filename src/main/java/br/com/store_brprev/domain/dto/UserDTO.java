package br.com.store_brprev.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.store_brprev.domain.enums.EPermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(
		value = { "audit"},
		allowGetters = true
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	
	@NotNull(message = "The email is mandatory because as login")
	@Email(message = "The login should be matches the email compliance")
	private String login;
	
	@NotNull(message = "The password is mandatory")
	@JsonInclude(Include.NON_NULL)
	private String password;
		
	@NotNull(message = "The permission is mandatory")
	private EPermissionLevel permission;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("client")
	private ClientDTO client;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("operator")
	private OperatorDTO operator;
	
	@Builder.Default
	private Boolean active = Boolean.TRUE;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("audit")	
	private AuditDTO audit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EPermissionLevel getPermission() {
		return permission;
	}

	public void setPermission(EPermissionLevel permission) {
		this.permission = permission;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public OperatorDTO getOperator() {
		return operator;
	}

	public void setOperator(OperatorDTO operator) {
		this.operator = operator;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((audit == null) ? 0 : audit.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permission != other.permission)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO("
					+ "id=" + id 
					+ ", login=" + login 
					+ ", password=" + password 
					+ ", permission=" + permission
					+ client != null ? ", client=" + client : "" 
					+ operator != null ? ", operator=" + operator : "" 
					+ ", active=" + active 
					+ ", audit="	+ audit 
					+ ")";
	}
		
}
