package br.gov.prefeitura.almoxarifado.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.prefeitura.almoxarifado.dao.ProdutoDAO;
import br.gov.prefeitura.almoxarifado.domain.Produto;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Produto.class)
public class ProdutoConvert implements Converter {

	private ProdutoDAO produtoDAO;
	
	public ProdutoConvert(){
		this.produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Produto retorno =null;
		
		if(value != null){
			retorno = this.produtoDAO.porId(Integer.parseInt(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value != null){
			Integer codigo = ((Produto) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
