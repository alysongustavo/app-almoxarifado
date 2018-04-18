package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.OrgaoDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.OrgaoFilter;
import br.gov.prefeitura.almoxarifado.domain.Orgao;
import br.gov.prefeitura.almoxarifado.service.OrgaoService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class OrgaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrgaoService orgaoService;
	
	@Inject
	private OrgaoDAO orgaoDAO;

	private List<Orgao> todosOrgaos = new ArrayList<>();
	
	private Orgao orgaoEdicao = new Orgao();
	private Orgao orgaoSelecionado;
	
	private OrgaoFilter filtro;
	
	public OrgaoBean(){
		this.filtro = new OrgaoFilter();
	}
	
	public void prepararNovoCadastro() {
		this.orgaoEdicao = new Orgao();
	}
	
	public void pesquisar() {
		this.todosOrgaos.clear();
		this.todosOrgaos = orgaoDAO.filtrados(filtro);
	}
	
	public void salvar() {
		orgaoService.salvar(this.orgaoEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Orgao salvo com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:orgao-table"));
	}
	
	public void excluir() {
		orgaoService.excluir(orgaoSelecionado);
		orgaoSelecionado = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Orgao excluído com sucesso!");
	}
	
	public void consultar() {
		this.todosOrgaos.clear();
		this.todosOrgaos = orgaoDAO.todos();
	}

	public Orgao getOrgaoEdicao() {
		return orgaoEdicao;
	}

	public void setOrgaoEdicao(Orgao orgaoEdicao) {
		this.orgaoEdicao = orgaoEdicao;
	}

	public List<Orgao> getTodosOrgaos() {
		return todosOrgaos;
	}

	public Orgao getOrgaoSelecionado() {
		return orgaoSelecionado;
	}

	public void setOrgaoSelecionado(Orgao orgaoSelecionado) {
		this.orgaoSelecionado = orgaoSelecionado;
	}

	public OrgaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(OrgaoFilter filtro) {
		this.filtro = filtro;
	}
	

}
