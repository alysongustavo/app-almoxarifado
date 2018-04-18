package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.dao.GrupoDAO;
import br.gov.prefeitura.almoxarifado.domain.Grupo;
import br.gov.prefeitura.almoxarifado.service.GrupoService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoService GrupoService;
	
	@Inject
	private GrupoDAO grupoDAO;

	private List<Grupo> todosGrupos = new ArrayList<>();
	private Grupo grupoEdicao = new Grupo();
	
	public void prepararNovoCadastro() {
		this.grupoEdicao = new Grupo();
	}
	
	public void salvar() {
		GrupoService.salvar(this.grupoEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Grupo salvo com sucesso!");
	}
	
	public void consultar() {
		this.todosGrupos.clear();
		this.todosGrupos = grupoDAO.todos();
	}

	public Grupo getGrupoEdicao() {
		return grupoEdicao;
	}

	public void setGrupoEdicao(Grupo grupoEdicao) {
		this.grupoEdicao = grupoEdicao;
	}

	public List<Grupo> getTodosGrupos() {
		return todosGrupos;
	}
	
	
	
	

}
