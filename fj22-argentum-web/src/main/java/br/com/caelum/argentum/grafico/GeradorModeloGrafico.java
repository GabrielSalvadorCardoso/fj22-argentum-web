package br.com.caelum.argentum.grafico;
/* *
 * GeradorModeloGrafico tem a responsabilidade de encapsular a complexidade da criação do Gráfico
 * */
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class GeradorModeloGrafico {
	private SerieTemporal serie;
	private int comeco;//O indice do começo deve ser maior ou igual ao numeros de diasParaCalculo do Indicador
	private int fim;
	private LineChartModel modeloGrafico;//Este é o grafico de linha
	
	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.modeloGrafico = new LineChartModel();
	}
	
	//O Indicador pode ser os objetos da classe MediaMovelSimple, MediaMovelPonderada ou qualquer uma que implemente Indicador
	public void plotaIndicador(Indicador indicador) {
		LineChartSeries chartSeries = new LineChartSeries(indicador.toString());//Esta é a linha do grafico
		
		for(int i = comeco; i <= fim; i++) {
			double valor = indicador.calcula(i, serie);//aqui obtemos a media entregando o indice e a serie
			chartSeries.set(i, valor);//a variavel 'i' serve como label para este ponto da linha
		}		
		//depois de configurarmos a linha com todos os pontos, a adicionamos ao grafico
		this.modeloGrafico.addSeries(chartSeries);
		this.modeloGrafico.setLegendPosition("w");
		this.modeloGrafico.setTitle("Indicadores");//configura o titulo do grafico
	}
	
	//Retornamos o grafico nos referindo a ele de forma generica
	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}
}
