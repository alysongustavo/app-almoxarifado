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

import br.gov.prefeitura.almoxarifado.dao.filter.InformativoFilter;
import br.gov.prefeitura.almoxarifado.domain.Informativo;

public class InformativoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Informativo porId(Integer id) {
		return manager.find(Informativo.class, id);
	}
	
	public Informativo buscarPorTitulo(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Informativo.class);
		criteria.add(Restrictions.eq("titulo", nome));
		return (Informativo) criteria.uniqueResult();
	}
	
	public List<Informativo> todas(){
		return manager.createQuery("SELECT c FROM Informativo c ORDER BY c.data_cadastro ASC ",Informativo.class).getResultList();
	}
	
	public void SalvarInformativo(Informativo informativo){
		manager.merge(informativo);
	}
	
	public void remover(Informativo informativo) {
		informativo = porId(informativo.getId());
		manager.remove(informativo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Informativo> filtrados(InformativoFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Informativo.class);
		
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
		
	}
		

}
