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

import br.gov.prefeitura.almoxarifado.dao.filter.ProdutoFilter;
import br.gov.prefeitura.almoxarifado.domain.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Produto porId(Integer id) {
		return manager.find(Produto.class, id);
	}
	
	public Produto buscarPorNome(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("nome", nome));
		return (Produto) criteria.uniqueResult();
	}
	
	public List<Produto> todas(){
		return manager.createQuery("FROM Produto",Produto.class).getResultList();
	}
	
	public void SalvarProduto(Produto produto){
		manager.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = porId(produto.getId());
		manager.remove(produto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class).createAlias("categoria", "c");
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getCategoria())) {
			// acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getCategoria(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
		

}
