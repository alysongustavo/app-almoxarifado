package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.domain.ItemSolicitado;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.domain.StatusSolicitacao;
import br.gov.prefeitura.almoxarifado.security.UsuarioSistema;
import br.gov.prefeitura.almoxarifado.service.CadastroSolicitacaoService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.utilitarios.Util;

@Named
@ViewScoped
public class CadastroSolicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO estoqueDAO;
	
	@Inject
	private  CadastroSolicitacaoService cadastroSolicitacaoService;
	
	@Produces
	@SolicitacaoEdicao
	private Solicitacao solicitacao;
	
	private Estoque estoqueLinhaEditavel;
	
	public CadastroSolicitacaoBean(){
		limpar();
	}
	
	public void inicializar() {
		if (this.solicitacao == null) {
			limpar();
		}
		
		this.solicitacao.adicionarItemVazio();
		
		this.recalcularSolicitacao();
		
	}
	
	private void limpar(){
		solicitacao = new Solicitacao();
		solicitacao.setNumero(Util.gerarNumeroSolicitacao(this.getUsuarioLogado().getSetor().getId()));
		solicitacao.setStatus_solicitacao(StatusSolicitacao.ORCAMENTO);
		solicitacao.setSetor(this.getUsuarioLogado().getSetor());
	}
	
	public void salvar() {
		this.solicitacao.removerItemVazio();
		
		try {
			this.solicitacao = this.cadastroSolicitacaoService.salvar(this.solicitacao);
		
			FacesUtil.addInfoMessage("Solicitacao salva com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		} finally {
			this.solicitacao.adicionarItemVazio();
		}
	}
	
	public void efetivar(){
		
		this.solicitacao.removerItemVazio();
		
		this.cadastroSolicitacaoService.efetivar(this.solicitacao);
	}
	
	public void recalcularSolicitacao() {
		if (this.solicitacao != null) {
			this.solicitacao.recalcularItemSolicitado();
		}
	}
	
	public void carregarEstoqueLinhaEditavel() {
		ItemSolicitado item = this.solicitacao.getItens_solicitado().get(0);
		item.setQuant_solicitada(BigDecimal.ONE);
		
		if (this.estoqueLinhaEditavel != null) {
			if (this.existeItemComEstoque(this.estoqueLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item na solicitacao com o estoque informado.");
			} else {
				item.setEstoque(this.estoqueLinhaEditavel);
				
				this.solicitacao.adicionarItemVazio();
				this.estoqueLinhaEditavel = null;
				
				
				this.solicitacao.recalcularItemSolicitado();
				
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
	
	public List<Estoque> completarEstoque(String nome) {
		return this.estoqueDAO.findAllPorNomeProduto(nome);
	}
	
	public void atualizarQuantidade(ItemSolicitado item, int linha) {
		if (item.getQuant_solicitada().compareTo(BigDecimal.ONE) == -1 ) {
			if (linha == 0) {
				item.setQuant_solicitada(BigDecimal.ONE);
			} else {
				this.getSolicitacao().getItens_solicitado().remove(linha);
			}
		}
		
		this.recalcularSolicitacao();
		
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
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
	
}
