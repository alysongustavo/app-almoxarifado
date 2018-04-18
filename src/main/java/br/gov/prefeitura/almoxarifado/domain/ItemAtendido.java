package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_atendido")
public class ItemAtendido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Solicitacao solicitacao_atend;
	private Estoque estoque;
	private BigDecimal quant_atendida;

	@Id
	@SequenceGenerator(name = "item_atendido_id", sequenceName = "item_atendido_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_atendido_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "estoque_id", nullable = false)
	public Estoque getEstoque() {
		return estoque;
	}
	
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@ManyToOne
	@JoinColumn(name = "solicitacao_id", nullable = false)
	public Solicitacao getSolicitacao_atend() {
		return solicitacao_atend;
	}

	public void setSolicitacao_atend(Solicitacao solicitacao_atend) {
		this.solicitacao_atend = solicitacao_atend;
	}

	@Column(nullable = false)
	public BigDecimal getQuant_atendida() {
		return quant_atendida;
	}

	public void setQuant_atendida(BigDecimal quant_atendida) {
		this.quant_atendida = quant_atendida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemAtendido other = (ItemAtendido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public boolean isEstoqueAssociado() {
		return this.getEstoque() != null && this.getEstoque().getId() != null;
	}
	
	@Transient
	public boolean isEstoqueSuficiente() {
		return this.getSolicitacao_atend().isAtendida() || this.getEstoque().getId() == null 
			|| this.getEstoque().getQuant_total().intValueExact() >= this.getQuant_atendida().intValueExact(); 
	}
	
	@Transient
	public boolean isEstoqueInsuficiente() {
		return !this.isEstoqueSuficiente();
	}
	
}
