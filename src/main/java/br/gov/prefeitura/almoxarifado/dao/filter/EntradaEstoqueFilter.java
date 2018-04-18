package br.gov.prefeitura.almoxarifado.dao.filter;

public class EntradaEstoqueFilter {

	private String fornecedor_nome;
	private String localizacao;

	public String getFornecedor_nome() {
		return fornecedor_nome;
	}

	public void setFornecedor_nome(String fornecedor_nome) {
		this.fornecedor_nome = fornecedor_nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

}
