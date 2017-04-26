package br.com.caelum.argentum.indicadores;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.modelo.Candle;
/**
* Serve para ajudar a fazer os testes.
*
* Recebe uma sequência de valores e cria candles com abertura, fechamento,
* minimo e maximo iguais(os valores de abertura, minimo, maximo, volume e data são irrelevantes neste caso),
* mil de volume e data de hoje. Finalmente, devolve
* tais candles encapsuladas em uma Serie Temporal.
**/
public class GeradorDeSerie {

	//Mosta Candlesticks com os valores dados e os encapsula em uma SerieTemporal
	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for(double d: valores) {
			//Monta a lista de Candlesticks com a lista de doubles passado
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}
	
	//OBS: a anotação 'double... d' faz com que o metodo possa receber de 0 a quantos doubles desejarmos
	//O metodo interpretará os valores recebidos como um array de double
}
