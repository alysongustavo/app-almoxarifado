package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.domain.Informativo;

@Named
@ViewScoped
public class DetalheInformativoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Informativo informativo = new Informativo();
	
	public DetalheInformativoBean(){

	}
	
	public Informativo getInformativo() {
		return informativo;
	}

	public void setInformativo(Informativo informativo) {
		this.informativo = informativo;
	}
	

}
