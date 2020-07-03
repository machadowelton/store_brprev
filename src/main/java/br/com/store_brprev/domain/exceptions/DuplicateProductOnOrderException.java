package br.com.store_brprev.domain.exceptions;

public class DuplicateProductOnOrderException extends BusinessException {

	private static final long serialVersionUID = -7580971052958056774L;

	public DuplicateProductOnOrderException(String message) {
		super(message);
	}

}
