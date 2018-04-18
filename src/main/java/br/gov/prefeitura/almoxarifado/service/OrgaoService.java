package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.OrgaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Orgao;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class OrgaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrgaoDAO orgaoDAO;
	
	@Transacional
	public void salvar(Orgao orgao){
		
		Orgao orgaoExistente = orgaoDAO.buscarPorNome(orgao.getNome());
		
		if(orgaoExistente != null && !orgaoExistente.equals(orgao)){
			throw new NegocioException("Já existe um orgao cadastrada com esse nome");
		}
		
		orgaoDAO.SalvarOrgao(orgao);
	}
	
	@Transacional
	public void excluir(Orgao orgao){
		orgaoDAO.remover(orgao);
	}
	
	

}
