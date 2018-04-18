package br.gov.prefeitura.almoxarifado.dao.filter;

import java.io.Serializable;

public class FornecedorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome_fantasia;
	private String responsavel;
	private String localizacao;

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
}
