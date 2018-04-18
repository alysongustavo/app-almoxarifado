package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import br.gov.prefeitura.almoxarifado.dao.filter.SolicitacaoFilter;
import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.domain.vo.DataQuantidade;

public class SolicitacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Solicitacao porId(Integer id) {
		return manager.find(Solicitacao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitacao> buscarPorEntidade(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Solicitacao.class)
				.createAlias("entidade", "e");
		
		criteria.add(Restrictions.eq("e.nome", nome));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Solicitacao>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitacao> buscarPorIdDoSetor(Integer id){
	  Query query = manager.createQuery("SELECT s FROM Solicitacao s WHERE s.setor.id = :id",Solicitacao.class);
	  query.setParameter("id", id);
	  return query.getResultList();
		
	}
	
	public List<Solicitacao> todas(){
		return manager.createQuery("SELECT c FROM Solicitacao c WHERE c.status_solicitacao != 'ORCAMENTO' ORDER BY c.data_solicitacao ASC ",Solicitacao.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitacao> filtrados(SolicitacaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Solicitacao.class);

		if (filtro.getStatus() != null && filtro.getStatus().length > 0) {
			criteria.add(Restrictions.in("status_solicitacao", filtro.getStatus()));
		}
		
		if (filtro.getData_de() != null) {
			criteria.add(Restrictions.ge("data_solicitacao", filtro.getData_de()));
		}
		
		if (filtro.getData_ate() != null) {
			criteria.add(Restrictions.le("data_solicitacao", filtro.getData_ate()));
		}

		return criteria.addOrder(Order.asc("id")).list();

	}
	
	public Solicitacao SalvarSolicitacao(Solicitacao solicitacao){
		return manager.merge(solicitacao);
	}
	
	public void remover(Solicitacao solicitacao) {
		solicitacao = porId(solicitacao.getId());
		manager.remove(solicitacao);
	}
	
	public int QuantidadeSolicitacaoPorSetor(Setor setor){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Solicitacao.class).createAlias("setor", "t");
		
		criteria.setProjection(Projections.rowCount())
			.add(Restrictions.eq("t.id", setor.getId()));
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public Map<Date, BigDecimal> totalDeSolicitacoesPorMes(){
		Session session = manager.unwrap(Session.class);
		
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.add(Calendar.MONTH, -12);
		
		Map<Date,BigDecimal> resultado = criarMapaVazio(dataInicial);
		
		
		Criteria criteria = session.createCriteria(Solicitacao.class);
		
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("date(data_solicitacao) as data", "date(data_solicitacao)", 
						new String[]{ "data" },
						new Type[]{StandardBasicTypes.DATE}))
				.add(Projections.rowCount())		
				)
				.add(Restrictions.ge("data_solicitacao", dataInicial.getTime()));
		// Select date(data_solicitacao) as data, sum(*) as quantidade from solicitacao where MONTH(data_solicitacao) = :dataInicial group by date(data_solicitacao)
		
		List<DataQuantidade> quant_por_data = criteria.setResultTransformer(Transformers.aliasToBean(DataQuantidade.class)).list();
		
		
		for(DataQuantidade dataQuantidade: quant_por_data){
			resultado.put(dataQuantidade.getData(), dataQuantidade.getQuantidade());
		}
		
		return  resultado;
		
	}
	
	private Map<Date, BigDecimal> criarMapaVazio(Calendar dataInicial) {
		
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date,BigDecimal> mapaInicial = new TreeMap<>();
		
		for(int i = 0; i <= 12; i++){
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.MONTH, 1);
		}
		
		return mapaInicial;
	}


}
