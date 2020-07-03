package br.com.store_brprev.security;

import br.com.store_brprev.domain.dto.UserDTO;

public interface IJWTService {
	
	String createToke(UserDTO user);
	
	String getLoginFromToken(String token);
	
	boolean validToken(String token);
	
}
