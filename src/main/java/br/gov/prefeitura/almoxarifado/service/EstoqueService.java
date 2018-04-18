package br.gov.prefeitura.almoxarifado.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.dao.ProdutoDAO;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.domain.Produto;
import br.gov.prefeitura.almoxarifado.util.jpa.Transacional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstoqueDAO estoqueDAO;

	@Inject
	private ProdutoDAO produtoDAO;

	@Transacional
	public void salvar(Estoque estoque) {

		if (estoque.getId() != null) {

		}
		Estoque estoqueExistente = estoqueDAO.findPorNomeProduto(estoque
				.getProduto().getNome());

		if (estoqueExistente != null && !estoqueExistente.equals(estoque)) {
			throw new NegocioException("Esse produto já tem um estoque montado");
		}

		estoqueDAO.SalvarEstoque(estoque);
	}

	@Transacional
	public void retirarDoEstoque(Estoque estoque) {
		estoqueDAO.SalvarEstoque(estoque);
	}

	@Transacional
	public void excluir(Estoque estoque) {
		estoqueDAO.remover(estoque);
	}

	public void consultarQuantidadeEstoque() {

		if (estoqueDAO.QuantidadeEstoque() <= 0) {
			throw new NegocioException(
					"O estoque encontra-se vazio, para gerar relatórios.");
		}

	}

	@Transacional
	public int montarEstoque() {

		List<Produto> produtos = this.produtoDAO.todas();
		int quantidade_atualizacao = 0;

		if (produtos.size() == 0) {
			throw new NegocioException(
					"Cadastre ao menos um produto, para montar o estoque!");
		}

		for (Produto produto : produtos) {

			Estoque estoque_consultado = estoqueDAO.findPorNomeProduto(produto
					.getNome());

			if (estoque_consultado == null) {
				quantidade_atualizacao++;
				Estoque estoque = new Estoque();
				estoque.setData_atualizacao(new Date());
				estoque.setProduto(produto);
				estoque.setQuant_total(BigDecimal.ZERO);
				estoqueDAO.SalvarEstoque(estoque);
			}

		}

		return quantidade_atualizacao;

	}

}
