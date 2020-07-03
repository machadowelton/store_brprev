package br.com.store_brprev.domain.enums;

public enum EPermissionLevel {
	
	CLIENT("CLIENT"),
	OPERATOR("OPERATOR");
	
	private final String value;
	
	EPermissionLevel(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
