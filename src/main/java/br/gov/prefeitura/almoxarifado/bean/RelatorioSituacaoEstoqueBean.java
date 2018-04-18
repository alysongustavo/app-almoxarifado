package br.gov.prefeitura.almoxarifado.bean;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.service.EstoqueService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.relatorio.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioSituacaoEstoqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpServletResponse response;

	@Inject
	private FacesContext facesContext;

	@Inject
	private EntityManager manager;
	
	@Inject
	private EstoqueService estoqueService;

	public void emitirEstoqueSolicitante() {

		try {
			
			estoqueService.consultarQuantidadeEstoque();
			
			FacesContext fContext = FacesContext.getCurrentInstance();
			ServletContext scontext = (ServletContext) fContext
					.getExternalContext().getContext();
			String pathRelatorio = scontext.getRealPath("") + File.separator
					+ "resources" + File.separator + File.separator + "imagens"
					+ File.separator;

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("IMAGE", pathRelatorio);
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

			String nome_arquivo = "estoque_solicitante_" + dateFormat.format(new Date())
					+ ".pdf";

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/relatorio_estoque_solicitante.jasper", this.response,
					parametros, nome_arquivo);

			Session session = manager.unwrap(Session.class);
			session.doWork(executor);

			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil
						.addErrorMessage("A execução do relatório não retornou dados.");
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());

			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm_notifica_operador:gerar_notificacao_operador"));

		}
	}
	
	public void emitirEstoqueAtendente() {

		try {
			
			estoqueService.consultarQuantidadeEstoque();
			
			FacesContext fContext = FacesContext.getCurrentInstance();
			ServletContext scontext = (ServletContext) fContext
					.getExternalContext().getContext();
			String pathRelatorio = scontext.getRealPath("") + File.separator
					+ "resources" + File.separator + File.separator + "imagens"
					+ File.separator;

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("IMAGE", pathRelatorio);
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

			String nome_arquivo = "estoque_atendente_" + dateFormat.format(new Date())
					+ ".pdf";

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/relatorio_estoque_atendente.jasper", this.response,
					parametros, nome_arquivo);

			Session session = manager.unwrap(Session.class);
			session.doWork(executor);

			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil
						.addErrorMessage("A execução do relatório não retornou dados.");
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());

			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm_notifica_operador:gerar_notificacao_operador"));

		}
	}

}