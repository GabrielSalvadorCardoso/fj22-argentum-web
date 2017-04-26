package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	//Apenas retorna o valor de abertura do Candle indicado por posição dentro do SerieTemporal
	//Lembrando que um SerieTemporal é um conjunto de Candles
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getAbertura();
	}

	@Override
	public String toString() {
		return "Abertura";
	}
}
