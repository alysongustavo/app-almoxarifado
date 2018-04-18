package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.InformativoDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.InformativoFilter;
import br.gov.prefeitura.almoxarifado.domain.Informativo;
import br.gov.prefeitura.almoxarifado.service.InformativoService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class InformativoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InformativoService informativoService;
	
	@Inject
	private InformativoDAO informativoDAO;

	private List<Informativo> todosInformativo = new ArrayList<>();
	
	private InformativoFilter filtro;
	
	private Informativo informativoEdicao = new Informativo();
	private Informativo informativoSelecionado;
	
	public InformativoBean(){
		this.filtro = new InformativoFilter();
	}
	
	public void prepararNovoCadastro() {
		this.informativoEdicao = new Informativo();
		
	}
	
	public void salvar() {
		informativoService.salvar(this.informativoEdicao);
		consultar();
		
		FacesUtil.addInfoMessage("Informativo salvo com sucesso!");
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:informativo-table"));
	}
	
	public void pesquisar() {
		this.todosInformativo.clear();
		this.todosInformativo = informativoDAO.filtrados(filtro);
	}
	
	public void excluir() {
		informativoService.excluir(informativoSelecionado);
		informativoSelecionado = null;
		
		consultar();
		
		FacesUtil.addInfoMessage("Informativo excluído com sucesso!");
	}
	
	public void consultar() {
		this.todosInformativo.clear();
		this.todosInformativo = informativoDAO.todas();
	}

	public Informativo getInformativoEdicao() {
		return informativoEdicao;
	}

	public void setInformativoEdicao(Informativo informativoEdicao) {
		this.informativoEdicao = informativoEdicao;
	}

	public List<Informativo> getTodosInformativo() {
		return todosInformativo;
	}

	public Informativo getInformativoSelecionado() {
		return informativoSelecionado;
	}

	public void setInformativoSelecionado(Informativo informativoSelecionado) {
		this.informativoSelecionado = informativoSelecionado;
	}

	public InformativoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(InformativoFilter filtro) {
		this.filtro = filtro;
	}
	

}
