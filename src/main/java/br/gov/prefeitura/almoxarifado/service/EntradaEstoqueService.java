package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.EntradaEstoqueDAO;
import br.gov.prefeitura.almoxarifado.domain.EntradaEstoque;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class EntradaEstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntradaEstoqueDAO entradaEstoqueDAO;
	
	@Transacional
	public void salvar(EntradaEstoque entradaEstoque){
				
		entradaEstoqueDAO.SalvarEntradaEstoque(entradaEstoque);
	}
	
	@Transacional
	public void excluir(EntradaEstoque entradaEstoque){
		entradaEstoqueDAO.remover(entradaEstoque);
	}
	
}
