package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Estoque.class)
public class EstoqueConvert implements Converter {

	private EstoqueDAO estoqueDAO;
	
	public EstoqueConvert(){
		this.estoqueDAO = CDIServiceLocator.getBean(EstoqueDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Estoque retorno =null;
		
		if(value != null){
			retorno = this.estoqueDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Estoque) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
