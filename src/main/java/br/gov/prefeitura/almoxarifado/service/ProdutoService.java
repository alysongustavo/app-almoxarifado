package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.ProdutoDAO;
import br.gov.prefeitura.almoxarifado.domain.Produto;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Transacional
	public void salvar(Produto produto){
		
		Produto produtoExistente = produtoDAO.buscarPorNome(produto.getNome());
		
		if(produtoExistente != null && !produtoExistente.equals(produto)){
			throw new NegocioException("Já existe um produto cadastrado com esse nome, tente com um novo nome");
		}
		
		produtoDAO.SalvarProduto(produto);
	}
	
	@Transacional
	public void excluir(Produto produto){
		produtoDAO.remover(produto);
	}
	
	

}
