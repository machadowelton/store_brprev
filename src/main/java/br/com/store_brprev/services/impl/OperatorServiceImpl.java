package br.com.store_brprev.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.store_brprev.domain.dto.OperatorDTO;
import br.com.store_brprev.domain.dto.UserDTO;
import br.com.store_brprev.domain.entities.Operator;
import br.com.store_brprev.domain.exceptions.OperatorNotFoundException;
import br.com.store_brprev.domain.exceptions.UniqueDataViolationException;
import br.com.store_brprev.services.IOperatorService;
import br.com.store_brprev.services.repositories.OperatorRepository;

@Service
public class OperatorServiceImpl implements IOperatorService {

	private final OperatorRepository operatorRepository;
	private final UserServiceImpl userService;
	
	private final ModelMapper mapper = new ModelMapper();
	
	TypeMap<Operator, OperatorDTO> typeMapEntityToDto = 
			mapper.createTypeMap(Operator.class, OperatorDTO.class)
				.addMapping((s) -> s.getCreatedAt(), (OperatorDTO d, Date v) -> d.getAudit().setCreatedAt(v))
				.addMapping((s) -> s.getUpdatedAt(), (OperatorDTO d, Date v) -> d.getAudit().setUpdatedAt(v))
				.addMappings((s) -> s.skip(OperatorDTO::setUser));
	
	TypeMap<OperatorDTO, Operator> typeMapDtoToEntity = 
			mapper.createTypeMap(OperatorDTO.class, Operator.class)
				.addMapping((s) -> s.getAudit().getCreatedAt(), (Operator d, Date v) -> d.setCreatedAt(v))
				.addMapping((s) -> s.getAudit().getUpdatedAt(), (Operator d, Date v) -> d.setUpdatedAt(v));
	
											
	public OperatorServiceImpl(final OperatorRepository operatorRepository, final UserServiceImpl userService) {		
		this.operatorRepository = operatorRepository;
		this.userService = userService;
	}

	@Override
	public OperatorDTO findById(Long id) {
		return operatorRepository.findById(id)
					.map((oe) -> {
						return Operator.builder()
								.id(oe.getId())
								.firstName(oe.getFirstName())
								.lastName(oe.getLastName())
								.cpf(oe.getCpf())
								.email(oe.getEmail())
								.createdAt(oe.getCreatedAt())
								.updatedAt(oe.getUpdatedAt())
								.build();
					})
					.map((o) -> mapper.map(o, OperatorDTO.class))
					.orElseThrow(() -> new OperatorNotFoundException("No found operator by id: " + id));
	}


	@Override
	public Page<OperatorDTO> find(Pageable pageable) {
		return operatorRepository.findAll(pageable)
				.map((oe) -> {
					return Operator.builder()
							.id(oe.getId())
							.firstName(oe.getFirstName())
							.lastName(oe.getLastName())
							.cpf(oe.getCpf())
							.email(oe.getEmail())
							.createdAt(oe.getCreatedAt())
							.updatedAt(oe.getUpdatedAt())
							.build();
				})
				.map((o) -> mapper.map(o, OperatorDTO.class));			
	}

	@Override
	public OperatorDTO update(Long id, OperatorDTO operator) {
		if(!operatorRepository.existsById(id))
			throw new OperatorNotFoundException("No operator found by id: " + id);
		operator.setId(id);
		Operator operatorEntity = mapper.map(operator, Operator.class);
		return mapper.map(operatorRepository.save(operatorEntity), OperatorDTO.class);
	}

	@Override
	public OperatorDTO save(OperatorDTO operator) {
		operatorRepository.findByCpfAndEmail(operator.getCpf(), operator.getEmail())
								.stream()
								.map((m) -> {
									if(m.getCpf() == operator.getCpf() && m.getEmail() == operator.getEmail())
										throw new UniqueDataViolationException("Already a operator using the cpf: " + m.getCpf() + " and mail:" + m.getEmail());
									else if (m.getCpf() == operator.getCpf() && m.getEmail() != operator.getEmail())
										throw new UniqueDataViolationException("Already a operator using the cpf: " + m.getCpf());
									else if (m.getCpf() != operator.getCpf() && m.getEmail() == operator.getEmail())
										throw new UniqueDataViolationException("Already a operator using the mail: " + m.getEmail());
									return m;
								});
		UserDTO user = userService.save(operator.getUser());
		operator.setUser(user);
		Operator operatorEntity = mapper.map(operator, Operator.class);
		return mapper.map(operatorRepository.save(operatorEntity), OperatorDTO.class);
	}

}
