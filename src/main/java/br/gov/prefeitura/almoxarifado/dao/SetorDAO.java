package br.gov.prefeitura.almoxarifado.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.gov.prefeitura.almoxarifado.dao.filter.SetorFilter;
import br.gov.prefeitura.almoxarifado.domain.Setor;

public class SetorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Setor porId(Integer id) {
		return manager.find(Setor.class, id);
	}

	public Setor buscarPorNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Setor.class);
		criteria.add(Restrictions.eq("nome", nome));
		return (Setor) criteria.uniqueResult();
	}

	public List<Setor> todos() {
		return manager
				.createQuery(
						"SELECT s FROM Setor s WHERE s.orgao.nome != 'ADMINISTRADOR'",
						Setor.class).getResultList();
	}

	public void SalvarSetor(Setor setor) {
		manager.merge(setor);
	}

	public void remover(Setor setor) {
		setor = porId(setor.getId());
		manager.remove(setor);
	}

	public Setor porCPF(String cpf) {
		Setor setor = null;

		try {
			setor = manager
					.createQuery(
							"SELECT s FROM Setor s WHERE s.cpf_responsavel = :cpf",
							Setor.class).setParameter("cpf", cpf)
					.getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return setor;
	}

	public Setor porEmail(String email) {
		Setor setor = null;

		try {
			setor = manager
					.createQuery(
							"SELECT s FROM Setor s WHERE lower(s.contato_setor.email) = :email",
							Setor.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return setor;
	}
	
	public Setor porToken(String token) {
		Setor setor = null;

		try {
			setor = manager
					.createQuery(
							"SELECT s FROM Setor s WHERE lower(s.token) = :token",
							Setor.class)
					.setParameter("token", token.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return setor;
	}

	@SuppressWarnings("unchecked")
	public List<Setor> filtrados(SetorFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Setor.class).createAlias("orgao", "o");

		criteria.add(Restrictions.ne("o.nome", "ADMINISTRADOR"));
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.list();

	}

}
