package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.GrupoDAO;
import br.gov.prefeitura.almoxarifado.domain.Grupo;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class GrupoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	@Transacional
	public void salvar(Grupo grupo){
		
		Grupo grupoExistente = grupoDAO.buscarPorNome(grupo.getNome());
		
		if(grupoExistente != null && !grupoExistente.equals(grupo)){
			throw new NegocioException("Já existe um grupo cadastrado com esse nome");
		}
		
		grupoDAO.SalvarGrupo(grupo);
	}
	
	

}
