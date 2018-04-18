package br.gov.prefeitura.almoxarifado.bean;

import java.io.File;
import java.io.Serializable;
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

import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.relatorio.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioSolicitacoesEfetuadaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Solicitacao solicitacao;

	@Inject
	private HttpServletResponse response;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private EntityManager manager;

	public void emitir() {
		
		FacesContext fContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fContext.getExternalContext().getContext();
		String pathRelatorio = scontext.getRealPath("") + File.separator + "resources" + File.separator
					+ File.separator + "imagens" + File.separator;
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("ID_SOLICITACAO", this.solicitacao.getId());
		parametros.put("IMAGE",pathRelatorio);
		
		String nome_arquivo = "solicitacao_"  + this.solicitacao.getNumero() + ".pdf";
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_solicitacao.jasper",
				this.response, parametros, nome_arquivo);
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

}