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

import br.gov.prefeitura.almoxarifado.dao.filter.FornecedorFilter;
import br.gov.prefeitura.almoxarifado.domain.Fornecedor;

public class FornecedorDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Fornecedor porId(Integer id) {
		return manager.find(Fornecedor.class, id);
	}
	
	public Fornecedor buscarPorNome(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		criteria.add(Restrictions.eq("nome_fantasia", nome));
		return (Fornecedor) criteria.uniqueResult();
	}
	
	public Fornecedor buscarPorCnpj(String cnpj){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		criteria.add(Restrictions.eq("cnpj", cnpj));
		return (Fornecedor) criteria.uniqueResult();
	}
	
	public List<Fornecedor> todas(){
		return manager.createQuery("FROM Fornecedor",Fornecedor.class).getResultList();
	}
	
	public void SalvarFornecedor(Fornecedor fornecedor){
		manager.merge(fornecedor);
	}
	
	public void remover(Fornecedor fornecedor) {
		fornecedor = porId(fornecedor.getId());
		manager.remove(fornecedor);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> filtrados(FornecedorFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		if (StringUtils.isNotBlank(filtro.getNome_fantasia())) {
			criteria.add(Restrictions.ilike("nome_fantasia", filtro.getNome_fantasia(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getResponsavel())) {
			// acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
			criteria.add(Restrictions.ilike("responsavel", filtro.getResponsavel(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome_fantasia")).list();
		
	}
		

}
