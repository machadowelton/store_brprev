package br.com.store_brprev.domain.exceptions;

public class UniqueDataViolationException extends BusinessException {	

	private static final long serialVersionUID = 458389538392703205L;
	
	public UniqueDataViolationException(String message) {
		super(message);
	}

}
