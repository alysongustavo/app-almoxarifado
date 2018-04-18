package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.security.UsuarioSistema;

@Named
@ViewScoped
public class SetorVisualizarPerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Setor setor_logado = null;
	
	public SetorVisualizarPerfilBean(){
		
		System.out.println("Chegou no " + SetorVisualizarPerfilBean.class.getSimpleName());
		
		this.setSetor_logado(getSetorLogado().getSetor());
	}
	
	public Setor getSetor_logado() {
		return setor_logado;
	}

	public void setSetor_logado(Setor setor_logado) {
		this.setor_logado = setor_logado;
	}
	
	public UsuarioSistema getSetorLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
}
