package br.gov.prefeitura.almoxarifado.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
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

import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.service.CadastroSolicitacaoService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.relatorio.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioMinhasSolicitacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;

	@Inject
	private HttpServletResponse response;

	@Inject
	private FacesContext facesContext;

	@Inject
	private EntityManager manager;
	
	@Inject
	private CadastroSolicitacaoService cadastroSolicitacaoService;
	

	public void emitir() {
		
		
		try{
			
			cadastroSolicitacaoService.consultarSolicitacaoPorSetor(setor);
			
			FacesContext fContext = FacesContext.getCurrentInstance();
			ServletContext scontext = (ServletContext) fContext.getExternalContext().getContext();
			String pathRelatorio = scontext.getRealPath("") + File.separator + "resources" + File.separator
						+ File.separator + "imagens" + File.separator;

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("ID_SETOR", setor.getId());
			parametros.put("IMAGE", pathRelatorio);

			String nome_arquivo = "solicitacoes_" + setor.getCpf_responsavel()
					+ ".pdf";

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/minhas_solicitacoes.jasper", this.response,
					parametros, nome_arquivo);

			Session session = manager.unwrap(Session.class);
			session.doWork(executor);

			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil
						.addErrorMessage("A execução do relatório não retornou dados.");
			}
		}catch(NegocioException e){
			FacesUtil.addErrorMessage(e.getMessage());
			
			RequestContext.getCurrentInstance().update(Arrays.asList("frm_notifica:gerar_notificacao"));
			
		}
		
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}