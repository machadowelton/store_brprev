package br.com.store_brprev.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.store_brprev.domain.dto.ClientDTO;

public interface IClientService {
	
	ClientDTO findById(Long id);
	
	Page<ClientDTO> find(Pageable pageable);
	
	ClientDTO save(ClientDTO client);
	
	ClientDTO update(Long id, ClientDTO client);
	
}
