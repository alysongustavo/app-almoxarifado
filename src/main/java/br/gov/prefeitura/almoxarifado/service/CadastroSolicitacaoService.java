package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.SolicitacaoDAO;
import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.domain.ItemAtendido;
import br.gov.prefeitura.almoxarifado.domain.ItemSolicitado;
import br.gov.prefeitura.almoxarifado.domain.Solicitacao;
import br.gov.prefeitura.almoxarifado.domain.StatusSolicitacao;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class CadastroSolicitacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoDAO solicitacaoDAO;

	@Transacional
	public Solicitacao salvar(Solicitacao solicitacao){
		
		if(solicitacao.isNovo()){
			solicitacao.setStatus_solicitacao(StatusSolicitacao.ORCAMENTO);
		}
		
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
	public Solicitacao salvarAtendimento(Solicitacao solicitacao){
		
		if(solicitacao.isAtendida()){
			throw new NegocioException("Atendimento da solicitacão não pode ser alterado no status "
					+ solicitacao.getStatus_solicitacao().getDescricao() + ".");
		}
		
		if(solicitacao.getItens_atendidos().isEmpty()){
			throw new NegocioException("A solicitacao deve possuir pelo menos um item atendido");
		}
		
		if(solicitacao.isQuantidadeTotalNegativo()){
			throw new NegocioException("Quantidade total itens da solicitação não pode ser negativo.");
		}
		
		
		for(ItemAtendido item: solicitacao.getItens_atendidos()){
			for(ItemSolicitado itemSolicitado: solicitacao.getItens_solicitado()){
				if(item.getEstoque().getId() == itemSolicitado.getEstoque().getId()){
					if(item.getQuant_atendida().compareTo(itemSolicitado.getQuant_solicitada()) == 1){
						throw new NegocioException("Quantidade total de " + item.getEstoque().getProduto().getNome() + " atendido é maior que a solicitada");
					}
				}
			}
		}
		
		solicitacao = this.solicitacaoDAO.SalvarSolicitacao(solicitacao);
		return solicitacao;
		
	}
	
	@Transacional
	public Solicitacao efetivar(Solicitacao solicitacao){
		
		if(solicitacao.getItens_solicitado().isEmpty()){
			throw new NegocioException("A solicitacao deve possuir pelo menos um item");
		}
		
		if(solicitacao.isQuantidadeTotalNegativo()){
			throw new NegocioException("Quantidade total itens da solicitação não pode ser negativo.");
		}
		
		solicitacao.setStatus_solicitacao(StatusSolicitacao.CRIADA);
		solicitacao = this.solicitacaoDAO.SalvarSolicitacao(solicitacao);
		return solicitacao;
		
	}
	
	@Transacional
	public Solicitacao efetivarAtendimento(Solicitacao solicitacao){
		
		if(solicitacao.getItens_atendidos().isEmpty()){
			throw new NegocioException("A solicitacao deve possuir pelo menos um item");
		}
		
		for(ItemAtendido item : solicitacao.getItens_atendidos()){
			
			if(item.getQuant_atendida().compareTo(item.getEstoque().getQuant_total()) == 1){
				throw new NegocioException("A quantidade atendida é maior que a disponivel no estoque");
			}
			
		}
		
		solicitacao.setStatus_solicitacao(StatusSolicitacao.ATENDIDA);
		solicitacao = this.solicitacaoDAO.SalvarSolicitacao(solicitacao);
		return solicitacao;
		
	}
	
	@Transacional
	public Solicitacao colocarEmAnalise(Solicitacao solicitacao){
		
		if(solicitacao.isAtendida()){
			throw new NegocioException("Solicitação não pode ser alterada no status "
					+ solicitacao.getStatus_solicitacao().getDescricao() + ".");
		}
		
		if(solicitacao.isEmAnalise()){
			throw new NegocioException("Solicitação já encontra-se "
					+ solicitacao.getStatus_solicitacao().getDescricao() + ".");
		}
		
		solicitacao.setStatus_solicitacao(StatusSolicitacao.EM_ANALISE);
		solicitacao = this.solicitacaoDAO.SalvarSolicitacao(solicitacao);
		
		return solicitacao;
		
	}
	
	public void consultarSolicitacaoPorSetor(Setor setor){
		
		if(solicitacaoDAO.QuantidadeSolicitacaoPorSetor(setor) <= 0){
			throw new NegocioException("Você não possui solicitação para geração de relatorios.");
		}
		
	}
	
	
}
