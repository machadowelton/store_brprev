package br.com.store_brprev.domain.exceptions;

public class InvalidTokenException extends BusinessException {

	private static final long serialVersionUID = -2673360436278297203L;

	public InvalidTokenException(String message) {
		super(message);
	}
	
}
