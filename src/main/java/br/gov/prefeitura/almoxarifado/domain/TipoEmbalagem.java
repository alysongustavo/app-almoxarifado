package br.gov.prefeitura.almoxarifado.domain;

public enum TipoEmbalagem {

	CAIXA("Caixa"),
	SACOLA("Sacola");
	
	private String descricao;
	
	TipoEmbalagem(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}
