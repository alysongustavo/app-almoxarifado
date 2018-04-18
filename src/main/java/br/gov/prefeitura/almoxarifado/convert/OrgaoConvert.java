package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.OrgaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Orgao;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Orgao.class)
public class OrgaoConvert implements Converter {

	private OrgaoDAO orgaoDAO;
	
	public OrgaoConvert(){
		this.orgaoDAO = CDIServiceLocator.getBean(OrgaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Orgao retorno = null;
		
		if(value != null){
			retorno = this.orgaoDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Orgao) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
