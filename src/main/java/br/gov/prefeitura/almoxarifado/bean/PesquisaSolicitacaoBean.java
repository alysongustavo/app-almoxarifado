package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.dao.SolicitacaoDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.SolicitacaoFilter;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.domain.ItemSolicitado;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.domain.StatusSolicitacao;
import br.gov.prefeitura.almoxarifado.service.CadastroSolicitacaoService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSolicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoDAO solicitacaoDAO;
	
	private SolicitacaoFilter filtro;
	
	private List<Solicitacao> todasSolicitacoes = new ArrayList<>();
	
	@Inject
	private  CadastroSolicitacaoService cadastroSolicitacaoService;

	private Solicitacao solicitacao;
	private Solicitacao solicitacaoSelecionada;
	private Solicitacao solicitacaoEmAnalise;
	
	
	private Estoque estoqueLinhaEditavel;
	
	public PesquisaSolicitacaoBean(){
		limpar();
		filtro = new SolicitacaoFilter();
	}
	
	public void inicializar() {
		if (this.solicitacao == null) {
			limpar();
		}
		
	}
	
	private void limpar(){
		solicitacao = new Solicitacao();
	}
	
	public void salvar() {

		try {
			this.solicitacao = this.cadastroSolicitacaoService.salvar(this.solicitacao);
		
			FacesUtil.addInfoMessage("Solicitacao efetuada com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		} finally {
			this.solicitacao.adicionarItemVazio();
		}
	}
	
	public void colocarEmAnalise(){
		
		try {
			this.solicitacao = this.cadastroSolicitacaoService.colocarEmAnalise(this.solicitacaoEmAnalise);
			FacesUtil.addInfoMessage("Solicitação colocada para analise com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		this.todasSolicitacoes.clear();
		this.todasSolicitacoes = solicitacaoDAO.filtrados(filtro);
	}
	
	public void consultar() {
		this.todasSolicitacoes.clear();
		this.todasSolicitacoes = solicitacaoDAO.todas();
	}
	
	public void carregarEstoqueLinhaEditavel() {
		ItemSolicitado item = this.solicitacao.getItens_solicitado().iterator().next();
		
		if (this.estoqueLinhaEditavel != null) {
			if (this.existeItemComEstoque(this.estoqueLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item na solicitacao com o estoque informado.");
			} else {
				item.setEstoque(this.estoqueLinhaEditavel);
				
				this.estoqueLinhaEditavel = null;
				
			}
		}
	}
	
	private boolean existeItemComEstoque(Estoque estoque) {
		boolean existeItem = false;
		
		for (ItemSolicitado item : this.getSolicitacao().getItens_solicitado()) {
			if (estoque.equals(item.getEstoque())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void atualizarQuantidade(ItemSolicitado item, int linha) {
		if (item.getQuant_solicitada().compareTo(BigDecimal.ONE) == -1 ) {
			if (linha == 0) {
				item.setQuant_solicitada(BigDecimal.ONE);
			} else {
				this.getSolicitacao().getItens_solicitado().remove(linha);
			}
		}
		
		
	}
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	public boolean isEditando() {
		return this.solicitacao.getId() != null;
	}

	public Estoque getEstoqueLinhaEditavel() {
		return estoqueLinhaEditavel;
	}

	public void setEstoqueLinhaEditavel(Estoque estoqueLinhaEditavel) {
		this.estoqueLinhaEditavel = estoqueLinhaEditavel;
	}

	public List<Solicitacao> getTodasSolicitacoes() {
		return todasSolicitacoes;
	}
	
	public StatusSolicitacao[] getStatusSolicitacao(){
		return StatusSolicitacao.values();
	}

	public SolicitacaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(SolicitacaoFilter filtro) {
		this.filtro = filtro;
	}

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public Solicitacao getSolicitacaoEmAnalise() {
		return solicitacaoEmAnalise;
	}

	public void setSolicitacaoEmAnalise(Solicitacao solicitacaoEmAnalise) {
		this.solicitacaoEmAnalise = solicitacaoEmAnalise;
	}
	

}
