package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.service.SetorService;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class SetorSolicitarRecuperacaoSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	
	private String email = "";
	
	public SetorSolicitarRecuperacaoSenhaBean(){
		System.out.println("Executou o bean: SetorSolicitarRecuperacaoSenhaBean");
	}
		
	public void recuperar(){
		try{
			setorService.enviarEmail(this.getEmail().trim());
		}catch(NegocioException e)
		{
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
