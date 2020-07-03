package br.com.store_brprev.domain.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4986438719413852351L;

	public BusinessException(String message) {
		super(message);
	}

}
