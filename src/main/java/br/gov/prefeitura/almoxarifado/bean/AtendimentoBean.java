package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.domain.ItemAtendido;
import br.gov.prefeitura.almoxarifado.domain.ItemSolicitado;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.service.CadastroSolicitacaoService;
import br.gov.prefeitura.almoxarifado.service.EstoqueService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO estoqueDAO;
	
	@Inject
	private  CadastroSolicitacaoService cadastroSolicitacaoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	@SolicitacaoEdicao
	private Solicitacao solicitacao;
	
	private Estoque estoqueLinhaEditavelAtend;
	
	public void inicializar() {
		solicitacao.adicionarItemAtendimentoVazio();
	}
	
	
	public void salvar() {
		this.solicitacao.removerItemAtendimentoVazio();
		
		try {
			this.solicitacao = this.cadastroSolicitacaoService.salvarAtendimento(this.solicitacao);
		
			FacesUtil.addInfoMessage("Atendimento salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		} finally {
			this.solicitacao.adicionarItemAtendimentoVazio();
		}
	}
	
	public void efetivar() {
		this.solicitacao.removerItemAtendimentoVazio();
		
		try {
			
			this.solicitacao = this.cadastroSolicitacaoService.efetivarAtendimento(this.solicitacao);
			
			for(ItemAtendido item: this.solicitacao.getItens_atendidos()){
				Estoque estoque = estoqueDAO.porId(item.getEstoque().getId());
				estoque.setQuant_total(estoque.getQuant_total().subtract(item.getQuant_atendida()));
				estoqueService.retirarDoEstoque(estoque);
			}
			
			FacesUtil.addInfoMessage("Atendimento efetuado com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void recalcularAtendimento() {
		if (this.solicitacao != null) {
			this.solicitacao.recalcularItemAtendido();
		}
	}
	
	public void carregarEstoqueLinhaEditavelAtendimento() {
		ItemAtendido item = this.solicitacao.getItens_atendidos().get(0);
		item.setQuant_atendida(BigDecimal.ONE);
		
		if (this.estoqueLinhaEditavelAtend != null) {
			if (!this.existeItemSolicitado(this.estoqueLinhaEditavelAtend)) {
				FacesUtil.addErrorMessage("A entidade não solicitou esse item, favor verificar.");
			}else if (this.existeItemAtendimentoComEstoque(this.estoqueLinhaEditavelAtend)) {
				FacesUtil.addErrorMessage("Já existe um item no atendimento com o estoque informado.");
			} else {
				item.setEstoque(this.estoqueLinhaEditavelAtend);
				
				this.solicitacao.adicionarItemAtendimentoVazio();
				this.estoqueLinhaEditavelAtend = null;
				
				this.solicitacao.recalcularItemAtendido();
				
			}
		}
	}
	
	private boolean existeItemAtendimentoComEstoque(Estoque estoque) {
		boolean existeItem = false;
		
		for (ItemAtendido item : this.getSolicitacao().getItens_atendidos()) {
			if (estoque.equals(item.getEstoque())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	private boolean existeItemSolicitado(Estoque estoque) {
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
	
	public void atualizarQuantidade(ItemAtendido item, int linha) {
		if (item.getQuant_atendida().compareTo(BigDecimal.ONE) == -1 ) {
			if (linha == 0) {
				item.setQuant_atendida(BigDecimal.ONE);
			} else {
				this.getSolicitacao().getItens_atendidos().remove(linha);
			}
		}
		
		this.recalcularAtendimento();
		
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

	public Estoque getEstoqueLinhaEditavelAtend() {
		return estoqueLinhaEditavelAtend;
	}

	public void setEstoqueLinhaEditavelAtend(Estoque estoqueLinhaEditavelAtend) {
		this.estoqueLinhaEditavelAtend = estoqueLinhaEditavelAtend;
	}

	
	
}
