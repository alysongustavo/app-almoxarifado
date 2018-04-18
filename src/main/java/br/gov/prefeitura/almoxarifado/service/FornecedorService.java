package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.FornecedorDAO;
import br.gov.prefeitura.almoxarifado.domain.Fornecedor;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class FornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@Transacional
	public void salvar(Fornecedor fornecedor){
		
		Fornecedor fornecedorExistenteNome = fornecedorDAO.buscarPorNome(fornecedor.getNome_fantasia());
		Fornecedor fornecedorExistenteCnpj = fornecedorDAO.buscarPorCnpj(fornecedor.getCnpj());
		
		if(fornecedorExistenteNome != null && !fornecedorExistenteNome.equals(fornecedor)){
			throw new NegocioException("Já existe um fornecedor cadastrado com esse nome");
		}
		
		if(fornecedorExistenteCnpj != null && !fornecedorExistenteCnpj.equals(fornecedor)){
			throw new NegocioException("Já existe um fornecedor cadastrado com esse cnpj");
		}
		
		fornecedorDAO.SalvarFornecedor(fornecedor);
	}
	
	@Transacional
	public void excluir(Fornecedor fornecedor){
		fornecedorDAO.remover(fornecedor);
	}
	
	

}
