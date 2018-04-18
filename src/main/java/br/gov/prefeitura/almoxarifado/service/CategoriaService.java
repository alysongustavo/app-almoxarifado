package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.CategoriaDAO;
import br.gov.prefeitura.almoxarifado.domain.Categoria;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class CategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Transacional
	public Categoria salvar(Categoria categoria){
		
		Categoria categoriaExistente = categoriaDAO.buscarPorNome(categoria.getNome());
		
		if(categoriaExistente != null && !categoriaExistente.equals(categoria)){
			throw new NegocioException("Já existe  uma categoria cadastrada com esse nome, tente novamente com um novo nome");
		}
		
		categoriaDAO.SalvarCategoria(categoria);
		return categoria;
	}
	
	@Transacional
	public void excluir(Categoria categoria){
		categoriaDAO.remover(categoria);
	}
	
	

}
