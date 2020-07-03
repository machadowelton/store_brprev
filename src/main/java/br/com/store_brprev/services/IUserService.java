package br.com.store_brprev.services;

import br.com.store_brprev.domain.dto.UserDTO;

public interface IUserService {
	
	UserDTO findById(Long id);
	
	UserDTO updatePassword(Long id, String oldPassword, String newPassowrd);
	
	UserDTO save(UserDTO user);
	
	UserDTO findByLogin(String login);
	
}
