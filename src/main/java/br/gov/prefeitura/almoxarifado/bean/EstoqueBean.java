package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;
import br.gov.prefeitura.almoxarifado.dao.FornecedorDAO;
import br.gov.prefeitura.almoxarifado.dao.ProdutoDAO;
import br.gov.prefeitura.almoxarifado.dao.filter.EstoqueFilter;
import br.gov.prefeitura.almoxarifado.domain.EntradaEstoque;
import br.gov.prefeitura.almoxarifado.domain.Estoque;
import br.gov.prefeitura.almoxarifado.domain.Fornecedor;
import br.gov.prefeitura.almoxarifado.domain.Perecivel;
import br.gov.prefeitura.almoxarifado.domain.Produto;
import br.gov.prefeitura.almoxarifado.domain.TipoEmbalagem;
import br.gov.prefeitura.almoxarifado.domain.TipoUnidade;
import br.gov.prefeitura.almoxarifado.service.EntradaEstoqueService;
import br.gov.prefeitura.almoxarifado.service.EstoqueService;
import br.gov.prefeitura.almoxarifado.service.NegocioException;
import br.gov.prefeitura.almoxarifado.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EstoqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstoqueService estoqueService;

	@Inject
	private EntradaEstoqueService entradaEstoqueService;

	@Inject
	private EstoqueDAO estoqueDAO;

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private FornecedorDAO fornecedorDAO;

	private List<Estoque> todosEstoque;
	private List<Produto> todosProdutos;
	private List<Fornecedor> todosFornecedores;

	private EstoqueFilter filtro;

	private Estoque estoqueEdicao;
	private Estoque estoqueSelecionado;
	private Estoque estoqueAbastecimentos;

	private EntradaEstoque entradaEstoqueEdicao = new EntradaEstoque();

	public EstoqueBean() {
		this.estoqueEdicao = new Estoque();
		this.filtro = new EstoqueFilter();
	}

	public void prepararNovoCadastro() {
		this.estoqueEdicao = new Estoque();
		this.estoqueEdicao.setProduto(new Produto());
	}

	public void prepararNovaEntradaEstoque() {
		this.entradaEstoqueEdicao = new EntradaEstoque();
		this.entradaEstoqueEdicao.setFornecedor(new Fornecedor());
		this.entradaEstoqueEdicao.setEstoque(this.estoqueEdicao);
	}

	public void salvar() {

		estoqueService.salvar(this.estoqueEdicao);
		consultar();

		FacesUtil.addInfoMessage("Estoque criado com sucesso!");

		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:estoque-table"));
	}

	public void abastecerEstoque() {

		
		if (entradaEstoqueEdicao.getQuantidade().compareTo(BigDecimal.ZERO) == 1) {

			estoqueEdicao.setQuant_total(estoqueEdicao.getQuant_total().add(
					entradaEstoqueEdicao.getQuantidade()));

			estoqueService.salvar(estoqueEdicao);
			entradaEstoqueService.salvar(entradaEstoqueEdicao);
			consultar();

			FacesUtil.addInfoMessage("Estoque abastecido com sucesso!");

			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:estoque-table"));
		}

	}

	public void montarEstoque() {

		try {
			int quant_atualizacao = estoqueService.montarEstoque();
			
			if(quant_atualizacao == 0){
				FacesUtil.addInfoMessage("Estoque já atualizado!");
			}else{
				FacesUtil.addInfoMessage("Estoque montado com, " + quant_atualizacao + " produtos");
			}
			
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm_notifica_operador:gerar_notificacao_operador"));
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage("Estoque já encontra-se montado!");

			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm_notifica_operador:gerar_notificacao_operador"));
		}
	}

	public void pesquisar() {
		this.todosEstoque.clear();
		this.todosEstoque = estoqueDAO.filtrados(filtro);
	}

	public void excluir() {
		estoqueService.excluir(estoqueSelecionado);
		estoqueSelecionado = null;

		consultar();

		FacesUtil.addInfoMessage("Estoque excluído com sucesso!");
	}

	public void consultar() {
		this.todosEstoque = estoqueDAO.todas();
		this.todosProdutos = produtoDAO.todas();
		this.todosFornecedores = fornecedorDAO.todas();
	}

	public Estoque getEstoqueEdicao() {
		return estoqueEdicao;
	}

	public void setEstoqueEdicao(Estoque estoqueEdicao) {
		this.estoqueEdicao = estoqueEdicao;
	}

	public EntradaEstoque getEntradaEstoqueEdicao() {
		return entradaEstoqueEdicao;
	}

	public void setEntradaEstoqueEdicao(EntradaEstoque entradaEstoqueEdicao) {
		this.entradaEstoqueEdicao = entradaEstoqueEdicao;
	}

	public List<Estoque> getTodosEstoque() {
		return todosEstoque;
	}

	public List<Produto> getTodosProdutos() {
		return todosProdutos;
	}

	public List<Fornecedor> getTodosFornecedores() {
		return todosFornecedores;
	}

	public TipoUnidade[] getTipoUnidade() {
		return TipoUnidade.values();
	}

	public TipoEmbalagem[] getTipoEmbalagem() {
		return TipoEmbalagem.values();
	}

	public Perecivel[] getPerecivel() {
		return Perecivel.values();
	}

	public Estoque getEstoqueSelecionado() {
		return estoqueSelecionado;
	}

	public void setEstoqueSelecionado(Estoque estoqueSelecionado) {
		this.estoqueSelecionado = estoqueSelecionado;
	}

	public EstoqueFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EstoqueFilter filtro) {
		this.filtro = filtro;
	}

	public Estoque getEstoqueAbastecimentos() {
		return estoqueAbastecimentos;
	}

	public void setEstoqueAbastecimentos(Estoque estoqueAbastecimentos) {
		this.estoqueAbastecimentos = estoqueAbastecimentos;
	}

}
