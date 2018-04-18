package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.NotificacaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Notificacao;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class NotificacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotificacaoDAO notificacaoDAO;
	
	@Transacional
	public void salvar(Notificacao notificacao){
		
		Notificacao notificacaoExistente = notificacaoDAO.buscarPorTitulo(notificacao.getTitulo());
		
		if(notificacaoExistente != null && !notificacaoExistente.equals(notificacao)){
			throw new NegocioException("Já existe uma notificacao cadastrada com esse titulo");
		}
		
		notificacaoDAO.SalvarNotificacao(notificacao);
	}
	
	

}
