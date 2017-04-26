package br.com.caelum.argentum.indicadores;
/* *
 * MediaMovePonderada recebe uma SerieTemporal e uma posição
 * fazemos uma media ponderada entre o Candle da posição indicada é os dois anteriores
 * dando maior peso aos Candles mais recentes
 * */
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador{
	private int diasParaCalculo;//numero de dias para entrar na media
	private Indicador outroIndicador;
	
	public MediaMovelPonderada(int dias, Indicador outroIndicador) {
		this.diasParaCalculo = dias;
		this.outroIndicador = outroIndicador;
	}
	
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		//Se a posição de começo é menor que o numero de dias a ser calculado, gera uma exeção
		if(posicao < (diasParaCalculo-1)) {
			throw new IndexOutOfBoundsException();
		}
		double soma = 0.0;
		int peso = 3;
		for(int i = posicao; i > posicao - diasParaCalculo; i--) {
			soma += (outroIndicador.calcula(i, serie) * peso);
			peso--;
		}
		return (soma / 6);//6 é a soma de todos os pesos
	}
	
	@Override
	public String toString() {
		return "MMP " + this.outroIndicador.toString();//Seŕa o titulo da linha
	}
}
