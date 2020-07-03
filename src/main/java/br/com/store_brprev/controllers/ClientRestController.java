package br.com.store_brprev.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.store_brprev.domain.dto.ClientDTO;
import br.com.store_brprev.services.IClientService;
import br.com.store_brprev.services.impl.ClientServiceImpl;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
	
	private final IClientService clientService;
		

	public ClientRestController(final ClientServiceImpl clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/{id}")
	public ClientDTO findById(@PathVariable("id") Long id) {
		return clientService.findById(id);
	}
	
	@GetMapping
	public Page<ClientDTO> find(Pageable pageable) {
		return clientService.find(pageable);
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO save(@RequestBody ClientDTO client) {
		return clientService.save(client);
	}
	
	
	@PutMapping("id")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ClientDTO update(@PathVariable("id") Long id, @RequestBody ClientDTO client) {
		return clientService.update(id, client);
	}
}
