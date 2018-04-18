package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.gov.prefeitura.almoxarifado.domain.Notificacao;

public class NotificacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Notificacao porId(Integer id) {
		return manager.find(Notificacao.class, id);
	}
	
	public Notificacao buscarPorTitulo(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Notificacao.class);
		criteria.add(Restrictions.eq("titulo", nome));
		return (Notificacao) criteria.uniqueResult();
	}
	
	public List<Notificacao> todas(){
		return manager.createQuery("SELECT c FROM Notificacao c ORDER BY c.data_cadastro ASC ",Notificacao.class).getResultList();
	}
	
	public void SalvarNotificacao(Notificacao notificacao){
		manager.merge(notificacao);
	}
	
	public void remover(Notificacao notificacao) {
		notificacao = porId(notificacao.getId());
		manager.remove(notificacao);
	}
		

}
