package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.SolicitacaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class PesquisaSolicitacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoDAO solicitacaoDAO;

	@Transacional
	public Solicitacao salvar(Solicitacao solicitacao){

		if(solicitacao.isNaoAlteravel()){
			throw new NegocioException("Pedido não pode ser alterado no status "
					+ solicitacao.getStatus_solicitacao().getDescricao() + ".");
		}
		
		if(solicitacao.getItens_solicitado().isEmpty()){
			throw new NegocioException("A solicitacao deve possuir pelo menos um item");
		}
		
		if(solicitacao.isQuantidadeTotalNegativo()){
			throw new NegocioException("Quantidade total itens da solicitação não pode ser negativo.");
		}
		
		solicitacao = this.solicitacaoDAO.SalvarSolicitacao(solicitacao);
		return solicitacao;
		
	}
	
	@Transacional
	public void excluir(Solicitacao solicitacao){
		solicitacaoDAO.remover(solicitacao);
	}
	
	
	
}