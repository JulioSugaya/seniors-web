package org.seniors.model;


public class Relatorio {

	private String evento;

	private String data;

	private String descricao;

	private String status;

	private String usuario;
	
	public Relatorio(String evento, String data, String descricao, String status, String usuario){
		this.evento = evento;
		this.data = data;
		this.descricao = descricao;
		this.status = status;
		this.usuario = usuario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}


}
