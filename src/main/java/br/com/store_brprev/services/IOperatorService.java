package br.com.store_brprev.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.store_brprev.domain.dto.OperatorDTO;

public interface IOperatorService {
	
	OperatorDTO findById(Long id);	
	
	Page<OperatorDTO> find(Pageable pageable);
	
	OperatorDTO save(OperatorDTO operator);
	
	OperatorDTO update(Long id, OperatorDTO operator);
	
}
