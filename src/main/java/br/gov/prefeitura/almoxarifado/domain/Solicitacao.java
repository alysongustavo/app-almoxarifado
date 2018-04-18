package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

@Entity
@Table(name = "solicitacao")
public class Solicitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Setor setor;
	private String numero;
	private Date data_solicitacao = DateTime.now().toDate();
	private List<ItemSolicitado> itens_solicitado = new ArrayList<>();
	private List<ItemAtendido> itens_atendidos = new ArrayList<>();
	private StatusSolicitacao status_solicitacao = StatusSolicitacao.ORCAMENTO;
	private BigDecimal quant_itens_solicitados = BigDecimal.ZERO;
	private BigDecimal quant_itens_atendido = BigDecimal.ZERO;
	private String observacao;
	
	@Id
	@SequenceGenerator(name = "solicitacao_id", sequenceName = "solicitacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "setor_id", nullable = false)
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@NotEmpty
	@Column(nullable = false)
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getData_solicitacao() {
		return data_solicitacao;
	}

	public void setData_solicitacao(Date data_solicitacao) {
		this.data_solicitacao = data_solicitacao;
	}
	
	@OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public List<ItemSolicitado> getItens_solicitado() {
		return itens_solicitado;
	}

	public void setItens_solicitado(List<ItemSolicitado> itens_solicitado) {
		this.itens_solicitado = itens_solicitado;
	}
	
	@OneToMany(mappedBy = "solicitacao_atend", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public List<ItemAtendido> getItens_atendidos() {
		return itens_atendidos;
	}

	public void setItens_atendidos(List<ItemAtendido> itens_atendidos) {
		this.itens_atendidos = itens_atendidos;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusSolicitacao getStatus_solicitacao() {
		return status_solicitacao;
	}

	public void setStatus_solicitacao(StatusSolicitacao status_solicitacao) {
		this.status_solicitacao = status_solicitacao;
	}

	@Column(name= "quant_item_solicitado", nullable = false, precision = 10, scale = 2)
	public BigDecimal getQuant_itens_solicitados() {
		return quant_itens_solicitados;
	}

	public void setQuant_itens_solicitados(BigDecimal quant_itens_solicitados) {
		this.quant_itens_solicitados = quant_itens_solicitados;
	}
	
	@Column(name= "quant_item_atendido", nullable = false, precision = 10, scale = 2)
	public BigDecimal getQuant_itens_atendido() {
		return quant_itens_atendido;
	}

	public void setQuant_itens_atendido(BigDecimal quant_itens_atendido) {
		this.quant_itens_atendido = quant_itens_atendido;
	}

	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Transient
	public boolean isNovo(){
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente(){
		return !isNovo();
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
		Solicitacao other = (Solicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void adicionarItemVazio() {
		if (this.isOrcamento()) {
			Estoque estoque = new Estoque();
			
			ItemSolicitado item = new ItemSolicitado();
			item.setEstoque(estoque);
			item.setSolicitacao(this);
			
			this.getItens_solicitado().add(0,item);
			
		}
	}
	
	public void recalcularItemSolicitado(){
		BigDecimal total = BigDecimal.ZERO;
		
		for(ItemSolicitado item: this.getItens_solicitado()){
			if(item.getEstoque() != null && item.getEstoque().getId() !=  null){
				total = total.add(item.getQuant_solicitada());
			}
		}
		
		this.setQuant_itens_solicitados(total);
	}
	
	public void adicionarItemAtendimentoVazio() {
		if (this.isCriada() || this.isEmAnalise()) {
			Estoque estoque = new Estoque();
			
			ItemAtendido item = new ItemAtendido();
			item.setEstoque(estoque);
			item.setSolicitacao_atend(this);
			
			this.getItens_atendidos().add(0,item);
		}
	}
	
	public void recalcularItemAtendido(){
		BigDecimal total = BigDecimal.ZERO;
		
		for(ItemAtendido item: this.getItens_atendidos()){
			if(item.getEstoque() != null && item.getEstoque().getId() !=  null){
				total = total.add(item.getQuant_atendida());
			}
		}
		
		this.setQuant_itens_atendido(total);
	}
	
	@Transient
	public boolean isOrcamento() {
		return StatusSolicitacao.ORCAMENTO.equals(this.getStatus_solicitacao());
	}

	public void removerItemVazio() {
		ItemSolicitado primeiroItem = this.getItens_solicitado().get(0);
		
		if (primeiroItem != null && primeiroItem.getEstoque().getId() == null) {
			this.getItens_solicitado().remove(0);
		}
	}
	
	public void removerItemAtendimentoVazio() {
		ItemAtendido primeiroItem = this.getItens_atendidos().get(0);
		
		if (primeiroItem != null && primeiroItem.getEstoque().getId() == null) {
			this.getItens_atendidos().remove(0);
		}
	}
	
	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}
	
	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	@Transient
	private boolean isCancelado() {
		return StatusSolicitacao.CANCELADA.equals(this.getStatus_solicitacao());
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}
	
	@Transient
	public boolean isAtendida() {
		return StatusSolicitacao.ATENDIDA.equals(this.getStatus_solicitacao());
	}
	
	@Transient
	public boolean isCriada() {
		return StatusSolicitacao.CRIADA.equals(this.getStatus_solicitacao());
	}
	
	@Transient
	public boolean isAlteravel() {
		return this.isOrcamento();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}
	
	@Transient
	public boolean isEmAnalise() {
		return StatusSolicitacao.EM_ANALISE.equals(this.getStatus_solicitacao());
	}
	
	@Transient
	public boolean isQuantidadeTotalNegativo() {
		return this.getQuant_itens_solicitados().compareTo(BigDecimal.ZERO) < 0;
	}
	
}
