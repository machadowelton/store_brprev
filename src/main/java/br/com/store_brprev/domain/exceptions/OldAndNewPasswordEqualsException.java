package br.com.store_brprev.domain.exceptions;

public class OldAndNewPasswordEqualsException extends BusinessException {

	private static final long serialVersionUID = 405983704347169705L;

	public OldAndNewPasswordEqualsException(String message) {
		super(message);
	}

}
