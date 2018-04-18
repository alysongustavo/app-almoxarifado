package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.gov.prefeitura.almoxarifado.domain.Grupo;

public class GrupoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Grupo porId(Integer id) {
		return manager.find(Grupo.class, id);
	}
	
	public Grupo buscarPorNome(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);
		criteria.add(Restrictions.eq("nome", nome));
		return (Grupo) criteria.uniqueResult();
	}
	
	public List<Grupo> todos(){
		return manager.createQuery("SELECT g FROM Grupo g WHERE g.descricao != 'ADMINISTRADOR'",Grupo.class).getResultList();
	}
	
	public List<Grupo> todosAdmin(){
		return manager.createQuery("SELECT g FROM Grupo g",Grupo.class).getResultList();
	}
	
	public void SalvarGrupo(Grupo grupo){
		manager.merge(grupo);
	}
	
	public void remover(Grupo grupo) {
		grupo = porId(grupo.getId());
		manager.remove(grupo);
	}

}
