package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.mail.Mailer;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioSolicitacaoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	@SolicitacaoEdicao
	private Solicitacao solicitacao;
	
	public void enviarSolicitacao() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(solicitacao.getSetor().getContato_setor().getEmail())
		.subject("PMC Orgão de almoxarifado - Solicitação: " + solicitacao.getNumero())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/solicitacao.template")))
		.put("solicitacao", solicitacao)
		.send();
		
		FacesUtil.addInfoMessage("Atendimento enviado por e-mail com sucesso!");
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	
}
