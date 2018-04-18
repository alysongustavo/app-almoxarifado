package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.gov.prefeitura.almoxarifado.dao.SolicitacaoDAO;

@Named
@RequestScoped
public class GraficoSolicitacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/yyyy");

	@Inject
	private SolicitacaoDAO solicitacaoDAO;
	
	private LineChartModel model;

	public void preRender() {
		this.model = new LineChartModel();
		
		adicionarSerie("Solicitações");
	}

	private void adicionarSerie(String rotulo) {
		Map<Date, BigDecimal> solicitacaoPorMes = this.solicitacaoDAO.totalDeSolicitacoesPorMes();
		
		ChartSeries series = new ChartSeries(rotulo);
		
		for (Date data : solicitacaoPorMes.keySet()) {
			series.set(DATE_FORMAT.format(data), solicitacaoPorMes.get(data));
		}
		
		this.model.addSeries(series);
	}
	
	public CartesianChartModel getModel() {
		return model;
	}

}
