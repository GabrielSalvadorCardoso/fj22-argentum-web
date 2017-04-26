package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void candlestickComDataNula() {
		new Candle(10, 20, 20, 20, 10000, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void valoresNegativos() {
		new Candle(-18, -5, -7, -5, -20, Calendar.getInstance());
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candle candle = new Candle(10.0, 10.0, 5.0, 10.0, 50.0, Calendar.getInstance());
		
		assertEquals(true, candle.isAlta());
	}
}
