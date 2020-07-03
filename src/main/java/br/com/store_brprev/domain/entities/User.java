package br.com.store_brprev.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.store_brprev.domain.enums.EPermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Audit {
	
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
	
	@NotNull(message = "The email is mandatory because as login")
	@Email(message = "The login should be matches the email compliance")
	@Column(unique = true)
	private String login;
	
	@NotNull(message = "The password is mandatory")
	private String password;
		
	@NotNull(message = "The permission is mandatory")
	@Enumerated(EnumType.STRING)
	private EPermissionLevel permission;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)	
	private Client client;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Operator operator;
	
	@Builder.Default
	private Boolean active = Boolean.TRUE;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
