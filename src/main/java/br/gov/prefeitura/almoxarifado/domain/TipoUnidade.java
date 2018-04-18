package br.gov.prefeitura.almoxarifado.domain;

public enum TipoUnidade {

	LITRO("Litro"),
	KILO("kilo"),
	RESMA("Resma"),
	CAIXA("Caixa"),
	UNIDADE("Unidade"),
	METRO("Metro");
	
	private String descricao;
	
	TipoUnidade(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}
