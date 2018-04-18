package br.gov.prefeitura.almoxarifado.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

@Path("/estoque")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
public class EstoqueResource {

	@GET
	public List<Estoque> listEstoque(){
		EstoqueDAO estoqueDAO = CDIServiceLocator.getBean(EstoqueDAO.class);
		return estoqueDAO.todas();
	}
}
