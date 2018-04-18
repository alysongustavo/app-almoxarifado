package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.CategoriaDAO;
import br.gov.prefeitura.almoxarifado.dao.ProdutoDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.ProdutoFilter;
import br.gov.prefeitura.almoxarifado.domain.Categoria;
import br.gov.prefeitura.almoxarifado.domain.Produto;
import br.gov.prefeitura.almoxarifado.service.ProdutoService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	private ProdutoFilter filtro;
	
	@Inject
	private ProdutoDAO produtoDAO;

	private List<Categoria> todasCategorias = new ArrayList<>();
	private List<Produto> todosProdutos = new ArrayList<>();
	
	private Produto produtoEdicao = new Produto();
	private Produto produtoSelecionado;
	
	public ProdutoBean(){
		filtro = new ProdutoFilter();
	}
	
	public void prepararNovoCadastro() {
		this.produtoEdicao = new Produto();
	}
	
	public void pesquisar() {
		this.todosProdutos.clear();
		this.todosProdutos = produtoDAO.filtrados(filtro);
	}
	
	public void salvar() {
		produtoService.salvar(this.produtoEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:produto-table"));
	}
	
	public void excluir() {
		produtoService.excluir(produtoSelecionado);
		produtoSelecionado = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Produto excluído com sucesso!");
	}
	
	public void consultar() {
		this.todosProdutos.clear();
		this.todasCategorias.clear();
		this.todosProdutos = produtoDAO.todas();
		this.todasCategorias = categoriaDAO.todas();
	}

	public Produto getProdutoEdicao() {
		return produtoEdicao;
	}

	public void setProdutoEdicao(Produto produtoEdicao) {
		this.produtoEdicao = produtoEdicao;
	}

	public List<Categoria> getTodasCategorias() {
		return todasCategorias;
	}

	public List<Produto> getTodosProdutos() {
		return todosProdutos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProdutoFilter filtro) {
		this.filtro = filtro;
	}
	

}
