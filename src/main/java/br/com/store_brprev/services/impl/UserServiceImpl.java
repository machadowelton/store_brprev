package br.com.store_brprev.services.impl;


import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.store_brprev.domain.dto.AuditDTO;
import br.com.store_brprev.domain.dto.UserDTO;
import br.com.store_brprev.domain.entities.User;
import br.com.store_brprev.domain.exceptions.OldAndNewPasswordEqualsException;
import br.com.store_brprev.domain.exceptions.UniqueDataViolationException;
import br.com.store_brprev.domain.exceptions.UserNotFoundException;
import br.com.store_brprev.services.IUserService;
import br.com.store_brprev.services.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;
	
	private final ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	TypeMap<User, UserDTO> typeMapEntityToDto = 
					mapper.createTypeMap(User.class, UserDTO.class)
						.addMapping((s) -> s.getCreatedAt(), (UserDTO d, Date v) -> d.getAudit().setCreatedAt(v))
						.addMapping((s) -> s.getUpdatedAt(), (UserDTO d, Date v) -> d.getAudit().setUpdatedAt(v))
						.addMappings(map -> map.skip(UserDTO::setPassword));
	
	TypeMap<UserDTO, User> typeMapDtoToEntity = 
			mapper.createTypeMap(UserDTO.class, User.class)
				.addMapping((s) -> s.getAudit().getCreatedAt(), (User d, Date v) -> d.setCreatedAt(v))
				.addMapping((s) -> s.getAudit().getUpdatedAt(), (User d, Date v) -> d.setUpdatedAt(v));

	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO findById(Long id) {
		return userRepository.findById(id)
					.map((m) -> mapper.map(m, UserDTO.class))
					.orElseThrow(() -> new UserNotFoundException("No match with user and password found"));
	}

	@Override
	public UserDTO updatePassword(Long id, String oldPassword, String newPassowrd) {
		if(oldPassword.equals(newPassowrd))
			throw new OldAndNewPasswordEqualsException("The old and the new passowrd cannot be same");
		User user = userRepository.findById(id)
							.map((m) -> m)
							.orElseThrow(() -> new UserNotFoundException("No user found by id: " + id));
		if(!passwordEncoder.matches(oldPassword, user.getPassword()))
			throw new UserNotFoundException("No match with user and password found");
		String cripytNewPassword = passwordEncoder.encode(newPassowrd);
		UserDTO userDto = mapper.map(user, UserDTO.class);
		userDto.setPassword(cripytNewPassword);
		userDto.setAudit(AuditDTO.builder().createdAt(user.getCreatedAt()).build());
		User userEntity = mapper.map(userDto, User.class);		
		return mapper.map(userRepository.save(userEntity), UserDTO.class);
	}

	@Override
	public UserDTO save(UserDTO user) {
		if(userRepository.existsByLogin(user.getLogin()))
			throw new UniqueDataViolationException("The mail: " + user.getLogin() + " already in use");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userEntity = mapper.map(user, User.class);		
		return mapper.map(userRepository.save(userEntity), UserDTO.class);
	}

	@Override
	public UserDTO findByLogin(String login) {
		return userRepository.findByLogin(login)
				.map((m) -> mapper.map(m, UserDTO.class))
				.orElseThrow(() -> new UserNotFoundException("No match with user and password found"));
	}

}
