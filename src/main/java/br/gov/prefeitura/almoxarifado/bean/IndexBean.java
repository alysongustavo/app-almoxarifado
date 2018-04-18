package br.gov.prefeitura.almoxarifado.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.MeterGaugeChartModel;

import br.gov.prefeitura.almoxarifado.dao.EstoqueDAO;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO estoqueDAO;

	private MeterGaugeChartModel meterGaugeModel1;
	
	@PostConstruct
	public void init() {
		createMeterGaugeModels();
	}

	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}

	private void createMeterGaugeModels() {
		meterGaugeModel1 = initMeterGaugeModel();
		meterGaugeModel1.setTitle("Estoque atual");
		meterGaugeModel1.setMax(20000);
		meterGaugeModel1.setGaugeLabel("Unidades");

	}
	
	@SuppressWarnings("serial")
	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(4000);
				add(8000);
				add(12000);
				add(16000);
				add(20000);
			}
		};

		return new MeterGaugeChartModel(estoqueDAO.quantidadeItemNoEstoque(), intervals);
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item selected", "Item Index: " + event.getItemIndex()
						+ ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
