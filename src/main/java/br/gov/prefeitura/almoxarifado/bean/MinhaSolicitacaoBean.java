package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.gov.prefeitura.almoxarifado.dao.SolicitacaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.security.UsuarioSistema;

@Named
@ViewScoped
public class MinhaSolicitacaoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoDAO solicitacaoDAO;
	
	private List<Solicitacao> todasSolicitacoes = new ArrayList<>();
	
	private Solicitacao solicitacao;
	private Solicitacao solicitacaoSelecionada;
	private Solicitacao solicitacaoAtendida;
	
	public MinhaSolicitacaoBean(){
		limpar();
	}
	
	public void inicializar() {
		if (this.solicitacao == null) {
			limpar();
		}
		
	}
	
	private void limpar(){
		solicitacao = new Solicitacao();
	}
	
	public void consultar() {
		this.todasSolicitacoes.clear();
		this.todasSolicitacoes = solicitacaoDAO.buscarPorIdDoSetor(this.getUsuarioLogado().getSetor().getId());
	}
	
	public UsuarioSistema getUsuarioLogado() {
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
	
	public List<Solicitacao> getTodasSolicitacoes() {
		return todasSolicitacoes;
	}
	
	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public Solicitacao getSolicitacaoAtendida() {
		return solicitacaoAtendida;
	}

	public void setSolicitacaoAtendida(Solicitacao solicitacaoAtendida) {
		this.solicitacaoAtendida = solicitacaoAtendida;
	}
	
}
