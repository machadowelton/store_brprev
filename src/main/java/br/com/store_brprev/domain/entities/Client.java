package br.com.store_brprev.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client extends Audit {
	
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
	
	@NotNull(message = "The first name is mandatory")
	private String firstName;
	
	@NotNull(message = "The last name is mandatory")
	private String lastName;	
	
	@NotNull(message = "The cpf document is mandatory")
	@Pattern(regexp = "\\d{11}", message = "the cpf document does not matches with pattern {00000000000}")
	@Column(unique = true)
	private String cpf;
	
	@Email(message = "The value not matches with email compliance")
	@NotNull(message = "The email is mandatory")
	@Column(unique = true)
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;	
	
	

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Builder
	public Client(Date createdAt, Date updatedAt, Long id,
			@NotNull(message = "The first name is mandatory") String firstName,
			@NotNull(message = "The last name is mandatory") String lastName,
			@NotNull(message = "The cpf document is mandatory") @Pattern(regexp = "\\d{11}", message = "the cpf document does not matches with pattern {00000000000}") String cpf,
			@Email(message = "The value not matches with email compliance") @NotNull(message = "The email is mandatory") String email,
			User user) {
		super(createdAt, updatedAt);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.email = email;
		this.user = user;
	}
	
}
