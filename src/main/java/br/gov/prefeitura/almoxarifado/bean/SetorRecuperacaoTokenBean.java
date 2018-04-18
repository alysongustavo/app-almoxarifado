package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.gov.prefeitura.almoxarifado.service.SetorService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class SetorRecuperacaoTokenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	
	private String token;
	
	private String senha = "";
	private String confirmaSenha = "";
	
	private HttpServletRequest request;
	
	public SetorRecuperacaoTokenBean(){
		
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		this.setToken(request.getParameter("token"));
		
	}
	
	public String alterarSenha()
	{

		if (this.getToken() != null && !this.getToken().isEmpty()) {

			if(!this.getSenha().isEmpty() && this.getSenha().equals(this.getConfirmaSenha())){
				setorService.enviarAlterarSenha(this.getToken(), senha);
				return "/login.xhtml";
			}
			
			FacesUtil.addErrorMessage("As senhas não conferem");
			return "";
			
			
		}
		
		return "";
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
