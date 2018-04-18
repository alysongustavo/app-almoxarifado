package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.FornecedorDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.FornecedorFilter;
import br.gov.prefeitura.almoxarifado.domain.Fornecedor;
import br.gov.prefeitura.almoxarifado.service.FornecedorService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	private FornecedorFilter filtro;

	private List<Fornecedor> todosFornecedor = new ArrayList<>();
	
	private Fornecedor fornecedorEdicao = new Fornecedor();
	private Fornecedor fornecedorSelecionado;
	
	public FornecedorBean(){
		filtro = new FornecedorFilter();
	}
	
	public void prepararNovoCadastro() {
		this.fornecedorEdicao = new Fornecedor();
	}
	
	public void salvar() {
		fornecedorService.salvar(this.fornecedorEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Fornecedor salvo com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:fornecedor-table"));
	}
	
	public void pesquisar() {
		this.todosFornecedor.clear();
		this.todosFornecedor = fornecedorDAO.filtrados(filtro);
	}
	
	public void excluir() {
		fornecedorService.excluir(fornecedorSelecionado);
		fornecedorSelecionado = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Fornecedor excluído com sucesso!");
	}
	
	public void consultar() {
		this.todosFornecedor.clear();
		this.todosFornecedor = fornecedorDAO.todas();
	}

	public Fornecedor getFornecedorEdicao() {
		return fornecedorEdicao;
	}

	public void setFornecedorEdicao(Fornecedor fornecedorEdicao) {
		this.fornecedorEdicao = fornecedorEdicao;
	}

	public List<Fornecedor> getTodosFornecedor() {
		return todosFornecedor;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public FornecedorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FornecedorFilter filtro) {
		this.filtro = filtro;
	}
	
	
	
}
