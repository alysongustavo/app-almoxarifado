package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.GrupoDAO;
import br.gov.prefeitura.almoxarifado.domain.Grupo;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter("grupoConverter")
public class GrupoConvert implements Converter {

	private GrupoDAO grupoDAO;
	
	public GrupoConvert(){
		this.grupoDAO = CDIServiceLocator.getBean(GrupoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Grupo retorno =null;
		
		if(value != null){
			retorno = this.grupoDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Grupo) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
