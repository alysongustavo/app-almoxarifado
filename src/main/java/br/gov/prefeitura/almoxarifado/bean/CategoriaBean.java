package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.CategoriaDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.CategoriaFilter;
import br.gov.prefeitura.almoxarifado.domain.Categoria;
import br.gov.prefeitura.almoxarifado.service.CategoriaService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaService categoriaService;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	private CategoriaFilter filtro;

	private List<Categoria> todasCategorias;
	private Categoria categoriaEdicao = new Categoria();
	private Categoria categoriaSelecionada;
	
	public CategoriaBean(){
		this.filtro = new CategoriaFilter();
	}
	
	public void prepararNovoCadastro() {
		this.categoriaEdicao = new Categoria();
	}
	
	public void salvar() {
		categoriaService.salvar(this.categoriaEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Categoria salva com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:categoria-table"));
		
	}
	
	public void pesquisar() {
		this.todasCategorias.clear();
		this.todasCategorias = categoriaDAO.filtrados(filtro);
	}
	
	public void excluir() {
		categoriaService.excluir(categoriaSelecionada);
		categoriaSelecionada = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Categoria excluída com sucesso!");
	}
	
	public void consultar() {
		this.todasCategorias = categoriaDAO.todas();
		
	}
	
	public Categoria getCategoriaEdicao() {
		return this.categoriaEdicao;
	}

	public void setCategoriaEdicao(Categoria categoriaEdicao) {
		this.categoriaEdicao = categoriaEdicao;
	}

	public List<Categoria> getTodasCategorias() {
		return this.todasCategorias;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CategoriaFilter filtro) {
		this.filtro = filtro;
	}
	
	
	
}
