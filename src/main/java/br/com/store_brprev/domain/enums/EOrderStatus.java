package br.com.store_brprev.domain.enums;

public enum EOrderStatus {

	OPEN("OPEN"),
	CLOSED("CLOSED");
	
	private final String value;
	
	private EOrderStatus(String status) {
		this.value = status;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
