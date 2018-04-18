package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.SetorDAO;
import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;
import br.gov.prefeitura.almoxarifado.util.mail.Mailer;
import br.gov.prefeitura.almoxarifado.util.utilitarios.GeneratedHashPasswordUtil;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

public class SetorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorDAO setorDAO;
	
	@Inject
	private Mailer mailer;
	
	@Transacional
	public void salvar(Setor setor){
		
		Setor setorExistente = setorDAO.porCPF(setor.getCpf_responsavel());
		
		if(setorExistente != null && !setorExistente.equals(setor)){
			throw new NegocioException("Já existe um responsavel com esse CPF cadastrado, tente novamente com um novo CPF");
		}
		
		setor.setToken(GeneratedHashPasswordUtil.generateHash(setor.getCpf_responsavel()));
		setorDAO.SalvarSetor(setor);
		
	}
	
	public void enviarEmail(String email) throws NegocioException{
		
		Setor setorExistente = setorDAO.porEmail(email);
		
		if(setorExistente != null && setorExistente.getContato_setor().getEmail().equals(email)){
			System.out.println("Nome do setor: " + setorExistente.getNome());
			
			MailMessage message = mailer.novaMensagem();
			
			message.to(setorExistente.getContato_setor().getEmail())
			.subject("Sistema Almoxarifado, recuperação de senha!")
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/recuperacao.template")))
			.put("setor", setorExistente)
			.send();
			
			FacesUtil.addInfoMessage("Solicitação de recuperação de senha enviado por e-mail com sucesso!");
			
			
		}else{
			throw new NegocioException("Não existe nenhum setor associado ao e-mail informado");
		}
		
		
	}
	
	@Transacional
	public void enviarAlterarSenha(String token, String senha) throws NegocioException{
		
		Setor setorExistente = setorDAO.porToken(token);
		
		if(setorExistente != null && setorExistente.getToken().equals(token)){
			System.out.println("Nome do setor: " + setorExistente.getNome());
			
			setorExistente.setSenha(senha);
			setorDAO.SalvarSetor(setorExistente);
			
			MailMessage message = mailer.novaMensagem();
			
			message.to(setorExistente.getContato_setor().getEmail())
			.subject("Sua senha foi alterada com sucesso!")
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/confirmacao.template")))
			.put("setor", setorExistente)
			.send();
			
			FacesUtil.addInfoMessage("Senha alterada com sucesso!");
			
			
		}else{
			throw new NegocioException("Ocorreu um erro na alteração da senha, favor repita a operação!");
		}
		
		
	}
	
	@Transacional
	public void excluir(Setor entidade){
		setorDAO.remover(entidade);
	}
	
}
