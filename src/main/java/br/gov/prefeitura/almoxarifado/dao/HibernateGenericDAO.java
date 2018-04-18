package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	@Inject
	private EntityManager manager;
	
	private Class<T> classeEntidade;
	
	@SuppressWarnings("unchecked")
	HibernateGenericDAO(){
		this.classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public T buscarPeloCodigo(ID id) {
		return manager.find(classeEntidade, id);
	}

	@Override
	public void salvar(T entidade) {
		manager.persist(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filtrar(T entidade, String... properties) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classeEntidade);
		
		if(properties != null){
			for(String propriedade: properties){
				try{
					Object valor = PropertyUtils.getProperty(entidade, propriedade);
					if(valor != null){
						if(valor instanceof String){
							criteria.add(Restrictions.ilike(propriedade, (String) valor, MatchMode.ANYWHERE));
						}else{
							criteria.add(Restrictions.eq(propriedade, valor));
						}
					}
				}catch(Exception e){
					throw new RuntimeException("Propriedade não encontrada", e);
				}
			}
		}
		
		
		return criteria.list();
	}
	
	protected EntityManager getEntityManager(){
		return manager;
	}
	
	protected Session getSession(){
		return manager.unwrap(Session.class);
	}

}
