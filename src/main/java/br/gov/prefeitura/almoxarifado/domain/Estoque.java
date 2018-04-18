package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Produto produto;
	private BigDecimal quant_total = BigDecimal.ZERO;
	private Date data_atualizacao = new Date();
	private List<EntradaEstoque> entrada_estoque = new ArrayList<>();;
	
	@Id
	@SequenceGenerator(name = "estoque_id", sequenceName = "estoque_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "produto_id")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getQuant_total() {
		return quant_total;
	}

	public void setQuant_total(BigDecimal quant_total) {
		this.quant_total = quant_total;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getData_atualizacao() {
		return data_atualizacao;
	}

	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}
	
	@OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public List<EntradaEstoque> getEntrada_estoque() {
		return entrada_estoque;
	}

	public void setEntrada_estoque(List<EntradaEstoque> entrada_estoque) {
		this.entrada_estoque = entrada_estoque;
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
		Estoque other = (Estoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
