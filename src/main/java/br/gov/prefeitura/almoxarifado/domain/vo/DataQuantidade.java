package br.gov.prefeitura.almoxarifado.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataQuantidade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal quantidade;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
