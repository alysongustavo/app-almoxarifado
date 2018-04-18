package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.CategoriaDAO;
import br.gov.prefeitura.almoxarifado.domain.Categoria;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConvert implements Converter {

	private CategoriaDAO categoriaDAO;
	
	public CategoriaConvert(){
		this.categoriaDAO = CDIServiceLocator.getBean(CategoriaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Categoria retorno =null;
		
		if(value != null){
			retorno = this.categoriaDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Categoria) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
