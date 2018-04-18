package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.gov.prefeitura.almoxarifado.dao.filter.OrgaoFilter;
import br.gov.prefeitura.almoxarifado.domain.Orgao;

public class OrgaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Orgao porId(Integer id) {
		return manager.find(Orgao.class, id);
	}
	
	public Orgao buscarPorNome(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Orgao.class);
		criteria.add(Restrictions.eq("nome", nome));
		return (Orgao) criteria.uniqueResult();
	}
	
	public List<Orgao> todos(){
		return manager.createQuery("SELECT o FROM Orgao o WHERE o.nome != 'ADMINISTRADOR'",Orgao.class).getResultList();
	}
	
	public List<Orgao> todosAdmin(){
		return manager.createQuery("SELECT o FROM Orgao o",Orgao.class).getResultList();
	}
	
	public void SalvarOrgao(Orgao orgao){
		manager.merge(orgao);
	}
	
	public void remover(Orgao orgao) {
		orgao = porId(orgao.getId());
		manager.remove(orgao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Orgao> filtrados(OrgaoFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Orgao.class);
		
		criteria.add(Restrictions.not(Restrictions.eq("nome", "ADMINISTRADOR")));
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
		

}
