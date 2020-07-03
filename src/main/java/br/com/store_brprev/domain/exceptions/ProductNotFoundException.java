package br.com.store_brprev.domain.exceptions;

public class ProductNotFoundException extends AnyNotFoundException {

	private static final long serialVersionUID = 7878382007240004863L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
