package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.dao.NotificacaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Notificacao;
import br.gov.prefeitura.almoxarifado.service.NotificacaoService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NotificacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotificacaoService notificacaoService;
	
	@Inject
	private NotificacaoDAO notificacaoDAO;

	private List<Notificacao> todasNotificacoes = new ArrayList<>();
	
	private Notificacao notificacaoEdicao = new Notificacao();
	
	public void prepararNovoCadastro() {
		this.notificacaoEdicao = new Notificacao();
		
	}
	
	public void salvar() {
		
		notificacaoService.salvar(this.notificacaoEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Notificacao salva com sucesso!");
	}
	
	public void consultar() {
		todasNotificacoes.clear();
		this.todasNotificacoes = notificacaoDAO.todas();
	}

	public Notificacao getNotificacaoEdicao() {
		return notificacaoEdicao;
	}

	public void setNotificacaoEdicao(Notificacao notificacaoEdicao) {
		this.notificacaoEdicao = notificacaoEdicao;
	}

	public List<Notificacao> getTodasNotificacoes() {
		return todasNotificacoes;
	}


}
