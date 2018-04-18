package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.InformativoDAO;
import br.gov.prefeitura.almoxarifado.domain.Informativo;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class InformativoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InformativoDAO informativoDAO;
	
	@Transacional
	public void salvar(Informativo informativo){
		
		Informativo informativoExistente = informativoDAO.buscarPorTitulo(informativo.getTitulo());
		
		if(informativoExistente != null && !informativoExistente.equals(informativo)){
			throw new NegocioException("Já existe um informativo cadastrado com esse nome");
		}
		
		informativoDAO.SalvarInformativo(informativo);
	}
	
	@Transacional
	public void excluir(Informativo informativo){
		informativoDAO.remover(informativo);
	}
	
	

}
