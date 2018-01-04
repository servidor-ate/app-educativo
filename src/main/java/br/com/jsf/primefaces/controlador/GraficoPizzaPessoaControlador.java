package br.com.jsf.primefaces.controlador;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.jsf.primefaces.repositorio.PessoaRepositorio;
import br.com.jsf.primefaces.utilidades.Utilidades;

@Named(value = "graficoPizzaPessoaControlador")
@RequestScoped
public class GraficoPizzaPessoaControlador {

	@Inject
	private PessoaRepositorio pessoaRepositorio;

	private PieChartModel pieChartModel;

	private boolean exibirMensagem;
	
	public boolean isExibirMensagem() {
		return exibirMensagem;
	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public void init() {
		this.pieChartModel = new PieChartModel();

		this.criarGrafico();
	}

	/***
	 * MONTA O GRÁFICO NA PÁGINA
	 */
	private void criarGrafico() {

		this.exibirMensagem = true;
		
		// CONSULTA OS DADOS PARA MONTAR O GRÁFICO
		Map<String, Integer> agrupamentoRegistrosMapa = this.pessoaRepositorio
				.recuperarRegistrosAgrupadoPorOrigemCadastro();

		if (agrupamentoRegistrosMapa != null && agrupamentoRegistrosMapa.size() > 0) {
		
			// INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
			agrupamentoRegistrosMapa.forEach((chave, valor) -> {
	
				pieChartModel.set(chave, valor);
	
			});
	
			pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
			pieChartModel.setShowDataLabels(true);
			pieChartModel.setLegendPosition("e");
			
		} else {
			
			pieChartModel.set("-", 0);
			pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
			pieChartModel.setShowDataLabels(true);
			pieChartModel.setLegendPosition("e");
			
		}

	}
}