package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "entrada_estoque")
public class EntradaEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Estoque estoque;
	private Fornecedor fornecedor;
	private BigDecimal quantidade;
	
	private TipoUnidade tipo_unidade;
	private TipoEmbalagem tipo_embalagem;
	
	// Localizacao
	private String localizacao;
	
	// Informacoes adicionais
	private Perecivel  perecivel;
	private Date data_validade;
	private BigDecimal quantidade_minima;
	private Date data_recebimento;

	// Informacoes fiscais
	private String nota_fiscal;
	private BigDecimal valor_notafiscal;

	@Id
	@SequenceGenerator(name = "entrada_estoque_id", sequenceName = "entrada_estoque_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrada_estoque_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "estoque_id",nullable = false)
	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@ManyToOne
	@JoinColumn(name = "fornecedor_id",nullable = false)
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	public TipoUnidade getTipo_unidade() {
		return tipo_unidade;
	}

	public void setTipo_unidade(TipoUnidade tipo_unidade) {
		this.tipo_unidade = tipo_unidade;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	public TipoEmbalagem getTipo_embalagem() {
		return tipo_embalagem;
	}

	public void setTipo_embalagem(TipoEmbalagem tipo_embalagem) {
		this.tipo_embalagem = tipo_embalagem;
	}

	@Size(max = 255)
	@Column(length=255)
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 5)
	public Perecivel getPerecivel() {
		return perecivel;
	}

	public void setPerecivel(Perecivel perecivel) {
		this.perecivel = perecivel;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	public Date getData_validade() {
		return data_validade;
	}

	public void setData_validade(Date data_validade) {
		this.data_validade = data_validade;
	}

	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getQuantidade_minima() {
		return quantidade_minima;
	}

	public void setQuantidade_minima(BigDecimal quantidade_minima) {
		this.quantidade_minima = quantidade_minima;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}

	@NotNull 
	@Size(max = 100)
	@Column(nullable = false,length=100)
	public String getNota_fiscal() {
		return nota_fiscal;
	}

	public void setNota_fiscal(String nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}

	@NotNull
	@Column(nullable = true, precision = 10, scale = 2)
	public BigDecimal getValor_notafiscal() {
		return valor_notafiscal;
	}

	public void setValor_notafiscal(BigDecimal valor_notafiscal) {
		this.valor_notafiscal = valor_notafiscal;
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
		EntradaEstoque other = (EntradaEstoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
