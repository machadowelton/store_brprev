package br.com.store_brprev.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.store_brprev.domain.dto.ClientDTO;
import br.com.store_brprev.domain.dto.UserDTO;
import br.com.store_brprev.domain.entities.Client;
import br.com.store_brprev.domain.exceptions.ClientNotFoundException;
import br.com.store_brprev.domain.exceptions.UniqueDataViolationException;
import br.com.store_brprev.services.IClientService;
import br.com.store_brprev.services.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements IClientService {	

	private final ClientRepository clientRepository;
	private final UserServiceImpl userService;
	
	
	private final ModelMapper mapper = new ModelMapper();
	TypeMap<Client, ClientDTO> typeMapEntityToDto = mapper.createTypeMap(Client.class, ClientDTO.class)
			.addMapping((s) -> s.getCreatedAt(), (ClientDTO d, Date v) -> d.getAudit().setCreatedAt(v))
			.addMapping((s) -> s.getUpdatedAt(), (ClientDTO d, Date v) -> d.getAudit().setUpdatedAt(v));

	public ClientServiceImpl(final ClientRepository clientRepository, final UserServiceImpl userService) {
		this.userService = userService;
		this.clientRepository = clientRepository;		
	}
					
	
	@Override
	public ClientDTO findById(Long id) {
		return clientRepository
					.findById(id)
					.map((ce) -> Client.builder()
								.id(ce.getId())
								.firstName(ce.getFirstName())
								.lastName(ce.getLastName())
								.cpf(ce.getCpf())
								.email(ce.getEmail())
								.createdAt(ce.getCreatedAt())
								.updatedAt(ce.getUpdatedAt())
								.build()
								)					
					.map((c) -> mapper.map(c, ClientDTO.class))
					.orElseThrow(() -> new ClientNotFoundException("No client found by id: " + id));
					
	}

	@Override
	public Page<ClientDTO> find(Pageable pageable) {
		return clientRepository.findAll(pageable)
				.map((ce) -> Client.builder()
						.id(ce.getId())
						.firstName(ce.getFirstName())
						.lastName(ce.getLastName())
						.cpf(ce.getCpf())
						.email(ce.getEmail())
						.createdAt(ce.getCreatedAt())
						.updatedAt(ce.getUpdatedAt())
						.build()
						)		
				.map((c) -> mapper.map(c, ClientDTO.class));
	}

	@Override
	public ClientDTO save(ClientDTO client) {
		List<Client> listClient = clientRepository.findByCpfOrEmail(client.getCpf(), client.getEmail());
		if(!listClient.isEmpty())
			listClient.stream()			
						.map((c) -> {
							if(c.getCpf() == client.getCpf() && c.getEmail() == client.getEmail())
								throw new UniqueDataViolationException("Already exists a consumer with mail: ".concat(c.getEmail()).concat(" and cpf: ").concat(c.getCpf()));
							else if (c.getCpf() == client.getCpf() && !(c.getEmail() == client.getEmail()))
								throw new UniqueDataViolationException("Already exists a consumer with cpf: ".concat(c.getCpf()));
							else if(c.getEmail() == client.getEmail() && !(c.getCpf() == client.getCpf()))
								throw new UniqueDataViolationException("Already exists a consumer with mail: ".concat(c.getEmail()));
							return c;
						});			
		UserDTO user = userService.save(client.getUser());
		client.setUser(user);
		ClientDTO cl = client;
		cl = null;
		Client clientEntity = mapper.map(cl, Client.class);
		return mapper.map(clientRepository.save(clientEntity), ClientDTO.class);
	}

	@Override
	public ClientDTO update(Long id, ClientDTO client) {
		if(!clientRepository.existsById(id))
			throw new ClientNotFoundException("No client found by id: " + id);
		client.setId(id);		
		Client clientEntity = mapper.map(client, Client.class, "a"); 
		return mapper.map(clientRepository.save(clientEntity), ClientDTO.class);
	}

}
