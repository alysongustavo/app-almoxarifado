package br.gov.prefeitura.almoxarifado.domain;

public enum Perecivel {

	SIM("Sim"),
	NAO("Não");
	
	private String descricao;
	
	Perecivel(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}
