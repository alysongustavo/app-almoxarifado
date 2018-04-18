package br.gov.prefeitura.almoxarifado.domain;

public enum StatusNotificacao {

	CRIADA("Criada"),
	PENDENTE("Pendente"),
	VISUALIZADA("Visualizada");
	
	private String descricao;

	private StatusNotificacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
