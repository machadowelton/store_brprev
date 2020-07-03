package br.com.store_brprev.domain.exceptions;

public class UserNotFoundException extends AnyNotFoundException {

	private static final long serialVersionUID = 1870994242718027945L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
