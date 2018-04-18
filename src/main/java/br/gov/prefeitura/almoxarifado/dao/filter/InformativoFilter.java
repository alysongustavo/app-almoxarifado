package br.gov.prefeitura.almoxarifado.dao.filter;

import java.io.Serializable;

public class InformativoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
