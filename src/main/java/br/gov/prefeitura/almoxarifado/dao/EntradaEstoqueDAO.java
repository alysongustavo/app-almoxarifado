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

import br.gov.prefeitura.almoxarifado.dao.filter.EntradaEstoqueFilter;
import br.gov.prefeitura.almoxarifado.domain.EntradaEstoque;
import br.gov.prefeitura.almoxarifado.domain.Estoque;

public class EntradaEstoqueDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public EntradaEstoque porId(Integer id) {
		return manager.find(EntradaEstoque.class, id);
	}
	
	public List<EntradaEstoque> todas() {
		return manager.createQuery("FROM EntradaEstoque", EntradaEstoque.class)
				.getResultList();
	}

	public void SalvarEntradaEstoque(EntradaEstoque entradaEstoque) {
		manager.merge(entradaEstoque);
	}

	public void remover(EntradaEstoque entradaEstoque) {
		entradaEstoque = porId(entradaEstoque.getId());
		manager.remove(entradaEstoque);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estoque> filtrados(EntradaEstoqueFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EntradaEstoque.class)
				.createAlias("estoque", "e")
				.createAlias("fornecedor", "f");
		
		if (StringUtils.isNotBlank(filtro.getFornecedor_nome())) {
			criteria.add(Restrictions.ilike("f.nome_fantasia", filtro.getFornecedor_nome(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getLocalizacao())) {
			criteria.add(Restrictions.ilike("localizacao", filtro.getLocalizacao(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
		
	}

}
