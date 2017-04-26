package br.com.caelum.argentum.modelo;
/* *
 * SerieTemporal apenas recebe e armazena uma lista de Candles
 *  com objetivo de encapsular a complexidade da criação da lista de Candles
 * */
import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;
	
	//O construtor receberá a lista de Candlesticks
	public SerieTemporal(List<Candle> candles) {
		if(candles == null){
			throw new IllegalArgumentException("SerieTemporal nao pode ter numero nulo de Candlestick's");
		}
		this.candles = candles;
	}
	
	//Retorna o candles especificado
	public Candle getCandle(int i) {
		return this.candles.get(i);
	}
	
	//Retorna o indice da ultima posição da lista
	public int getUltimaPosicao() {
		return this.candles.size() - 1;
	}

}
