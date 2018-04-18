package br.gov.prefeitura.almoxarifado.domain;

public enum StatusSolicitacao {

	ORCAMENTO("ORÇAMENTO"),
	CRIADA("CRIADA"),
	EM_ANALISE("EM ANALISE"),
	ATENDIDA("ATENDIDA"),
	CANCELADA("CANCELADA");
	
	
	private String descricao;

	private StatusSolicitacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
