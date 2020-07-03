package br.com.store_brprev.domain.exceptions;

public class ClientNotFoundException extends AnyNotFoundException {

	private static final long serialVersionUID = 7830513854703661340L;

	public ClientNotFoundException(String message) {
		super(message);
	}

}
