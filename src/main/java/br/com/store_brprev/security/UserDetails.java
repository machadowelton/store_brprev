package br.com.store_brprev.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.store_brprev.domain.dto.UserDTO;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

	
	private String login;
	
	private String userPassword;
	
	
	private Boolean active;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public UserDetails(String login, String userPassword, Boolean active,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.login = login;
		this.userPassword = userPassword;
		this.active = active;
		this.authorities = authorities;
	}

	
	static UserDetails create(UserDTO user) {
		return new UserDetails(user.getLogin(), user.getPassword(), user.getActive(), Arrays.asList(new SimpleGrantedAuthority(user.getPermission().getValue())));
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {

		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {		
		return active;
	}

}
