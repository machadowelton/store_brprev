package br.com.store_brprev.domain.exceptions;

public class OrderClosedException extends BusinessException {

	private static final long serialVersionUID = -2993038495538129335L;

	public OrderClosedException(String message) {
		super(message);
	}

}
