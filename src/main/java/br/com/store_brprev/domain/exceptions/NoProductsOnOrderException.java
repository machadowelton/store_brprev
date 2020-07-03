package br.com.store_brprev.domain.exceptions;

public class NoProductsOnOrderException extends BusinessException {

	private static final long serialVersionUID = -2996959951987817901L;
	
	public NoProductsOnOrderException(String message) {
		super(message);
	}

}
