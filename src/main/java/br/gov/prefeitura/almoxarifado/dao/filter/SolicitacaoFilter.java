package br.gov.prefeitura.almoxarifado.dao.filter;

import java.io.Serializable;
import java.util.Date;

import br.gov.prefeitura.almoxarifado.domain.StatusSolicitacao;

public class SolicitacaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private StatusSolicitacao[] status;
	private Date data_de;
	private Date data_ate;
	
	public StatusSolicitacao[] getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacao[] status) {
		this.status = status;
	}

	public Date getData_de() {
		return data_de;
	}

	public void setData_de(Date data_de) {
		this.data_de = data_de;
	}

	public Date getData_ate() {
		return data_ate;
	}

	public void setData_ate(Date data_ate) {
		this.data_ate = data_ate;
	}

}
