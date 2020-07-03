package br.com.store_brprev.domain.exceptions;

public class AnyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7225231283625399063L;

	public AnyNotFoundException(String message) {
		super(message);
	}

}
