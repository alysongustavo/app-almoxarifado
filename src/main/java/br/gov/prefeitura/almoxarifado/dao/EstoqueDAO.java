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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.gov.prefeitura.almoxarifado.dao.filter.EstoqueFilter;
import br.gov.prefeitura.almoxarifado.domain.Estoque;

public class EstoqueDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Estoque porId(Integer id) {
		return manager.find(Estoque.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Estoque> findAllPorNomeProduto(String nome) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estoque.class).createAlias(
				"produto", "p");

		criteria.add(Restrictions.ilike("p.nome", nome, MatchMode.ANYWHERE));

		return criteria.list();
	}

	public List<Estoque> todas() {
		return manager.createQuery("FROM Estoque", Estoque.class)
				.getResultList();
	}

	public void SalvarEstoque(Estoque estoque) {
		manager.merge(estoque);
	}

	public void remover(Estoque estoque) {
		estoque = porId(estoque.getId());
		manager.remove(estoque);
	}

	@SuppressWarnings("unchecked")
	public List<Estoque> filtrados(EstoqueFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estoque.class).createAlias(
				"produto", "p");

		if (StringUtils.isNotBlank(filtro.getProduto_nome())) {
			criteria.add(Restrictions.ilike("p.nome", filtro.getProduto_nome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("id")).list();

	}

	public Estoque findPorNomeProduto(String nome) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estoque.class).createAlias(
				"produto", "p");

		criteria.add(Restrictions.eq("p.nome", nome));

		return (Estoque) criteria.uniqueResult();
	}
	
	public int QuantidadeEstoque(){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estoque.class);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	public Integer quantidadeItemNoEstoque(){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estoque.class);
		
		criteria.setProjection(Projections.sum("quant_total"));
		
		return ((Number) criteria.uniqueResult()).intValue();
		
		
	}

}
