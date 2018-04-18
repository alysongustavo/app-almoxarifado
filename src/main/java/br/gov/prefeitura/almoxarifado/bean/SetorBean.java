package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.GrupoDAO;
import br.gov.prefeitura.almoxarifado.dao.OrgaoDAO;
import br.gov.prefeitura.almoxarifado.dao.SetorDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.SetorFilter;
import br.gov.prefeitura.almoxarifado.domain.Grupo;
import br.gov.prefeitura.almoxarifado.domain.Orgao;
import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.service.SetorService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private SetorDAO setorDAO;
	
	@Inject
	private OrgaoDAO orgaoDAO;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	private SetorFilter filtro;

	private List<Setor> todosSetores;
	private List<Orgao> todosOrgaos;
	
	private List<Grupo> todosGruposAdmin;
	private List<Grupo> todosGrupos;
	
	private Setor setorEdicao = new Setor();
	private Setor setorSelecionado;
	
	private String email;
		
	private String senha = "";
	private String confirmaSenha = "";
	
	public SetorBean(){
		this.filtro = new SetorFilter();
		
	}
	
	public void prepararNovoCadastro() {
		this.setorEdicao = new Setor();		
	}
	
	public void salvar() {
		setorService.salvar(this.setorEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Setor salvo com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:setor-table"));
	}
		
	public void pesquisar() {
		this.todosSetores.clear();
		this.todosSetores = setorDAO.filtrados(filtro);
	}
	
	public void excluir() {
		setorService.excluir(setorSelecionado);
		setorSelecionado = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Setor excluido com sucesso!");
	}
	
	public void consultar() {
		this.todosSetores = setorDAO.todos();
		this.todosOrgaos = orgaoDAO.todos();
		this.todosGrupos = grupoDAO.todos();
	}
	
	public void consultarAdmin() {
		this.todosSetores = setorDAO.todos();
		this.todosOrgaos = orgaoDAO.todosAdmin();
		this.todosGruposAdmin = grupoDAO.todosAdmin();
	}

	public Setor getSetorEdicao() {
		return setorEdicao;
	}

	public void setSetorEdicao(Setor setorEdicao) {
		this.setorEdicao = setorEdicao;
	}

	public List<Setor> getTodosSetores() {
		return todosSetores;
	}

	public List<Orgao> getTodosOrgaos() {
		return todosOrgaos;
	}
	
	public List<Grupo> getTodosGrupos() {
		return todosGrupos;
	}
	
	public List<Grupo> getTodosGruposAdmin() {
		return todosGruposAdmin;
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

	public SetorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(SetorFilter filtro) {
		this.filtro = filtro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
}
