package br.com.store_brprev.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.store_brprev.domain.dto.UserDTO;
import br.com.store_brprev.services.IUserService;
import br.com.store_brprev.services.impl.UserServiceImpl;

@Component
public class UserDetailsServiceCustom implements UserDetailsService {

	
	private final IUserService userService;
	
		
	public UserDetailsServiceCustom(final UserServiceImpl userService) {
		this.userService = userService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userService.findByLogin(username);
		UserDetails userDetails = br.com.store_brprev.security.UserDetails.create(user);
		return userDetails;
	}

}
