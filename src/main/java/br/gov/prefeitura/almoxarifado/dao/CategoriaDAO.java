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

import br.gov.prefeitura.almoxarifado.dao.filter.CategoriaFilter;
import br.gov.prefeitura.almoxarifado.domain.Categoria;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Categoria porId(Integer id) {
		return manager.find(Categoria.class, id);
	}
	
	public Categoria buscarPorNome(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("nome", nome));
		return (Categoria) criteria.uniqueResult();
	}
	
	public List<Categoria> todas(){
		return manager.createQuery("FROM Categoria",Categoria.class).getResultList();
	}
	
	public void SalvarCategoria(Categoria categoria){
		manager.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = porId(categoria.getId());
		manager.remove(categoria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(CategoriaFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
		

}
