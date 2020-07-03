package br.com.store_brprev.domain.exceptions;

public class ProductNotAvaliableException extends BusinessException {

	private static final long serialVersionUID = -8957690827004867899L;
	
	public ProductNotAvaliableException(String message) {
		super(message);
	}
	
}
