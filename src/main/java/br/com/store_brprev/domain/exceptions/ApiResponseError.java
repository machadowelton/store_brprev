package br.com.store_brprev.domain.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;

@Builder
public class ApiResponseError implements Serializable {

	private static final long serialVersionUID = 898518580210663714L;
	
	private String mensagem;

	@Builder.Default
	private String data = new Date().toString();
	
	private String path;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
		

}
