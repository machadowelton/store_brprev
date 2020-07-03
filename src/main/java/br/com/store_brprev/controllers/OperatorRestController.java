package br.com.store_brprev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.store_brprev.domain.dto.OperatorDTO;
import br.com.store_brprev.services.IOperatorService;
import br.com.store_brprev.services.impl.OperatorServiceImpl;

@RestController
@RequestMapping("operators")
public class OperatorRestController {
	
	
	private final IOperatorService operatorService;

	public OperatorRestController(OperatorServiceImpl operatorService) {
		this.operatorService = operatorService;
	}
	
	
	
	@GetMapping("/{id}")
	public OperatorDTO findById(@PathVariable("id") Long id) {
		return operatorService.findById(id);
	}
	
}
