package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.FornecedorDAO;
import br.gov.prefeitura.almoxarifado.domain.Fornecedor;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Fornecedor.class)
public class FornecedorConvert implements Converter {

	private FornecedorDAO fornecedorDAO;
	
	public FornecedorConvert(){
		this.fornecedorDAO = CDIServiceLocator.getBean(FornecedorDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Fornecedor retorno =null;
		
		if(value != null){
			retorno = this.fornecedorDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Fornecedor) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
