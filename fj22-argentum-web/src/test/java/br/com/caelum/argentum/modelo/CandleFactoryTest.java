package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Calendar;
import org.junit.Test;
import junit.framework.Assert;

import java.util.List;

public class CandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandleFactory factory = new CandleFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		//SINTAXE: Assert.assertEquals(valorEsperado, valorTestado, tolerancia);
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3 ,candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();
		
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandleFactory factory = new CandleFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(0.0, candle.getAbertura(), 0.00001);
		Assert.assertEquals(0.0, candle.getFechamento(), 0.00001);
		Assert.assertEquals(0.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(0.0, candle.getMinimo(), 0.00001);
		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);
		
		CandleFactory factory = new CandleFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	/*@Test(expected=IllegalArgumentException.class)
	public void candlestickEmOrdemCrescentePorValor() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(15.5, 100, hoje);
		Negociacao n2 = new Negociacao(10.5, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1, n2);
				
		CandlestickFactory factory = new CandlestickFactory();
		factory.constroiCandleParaData(hoje, negociacoes);
	}*/
	
	@Test
	public void paraNegociacoesDeTresDiasDiferentesGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();
		//cria 4 negociacoes para o dia 'hoje' para o primeiro Candlestick
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
		
		//cria mais 2 negociacoes para o dia posterior (SEGUNDO CANDLESTICK)
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
		
		//cria mais 2 negociacoes para o dia depois (TERCEIRO CANDLESTICK)
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);
		
		//cria uma lista com essas negociacoes
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4,
													negociacao5, negociacao6, negociacao7, negociacao8);
		
		CandleFactory factory = new CandleFactory();
		
		//constroiCandles() pega a lista completa de negociacoes e divide as negociacoes de mesma data em um Candlesstick
		//este metodo retorna uma lista com estes candlesticks
		List<Candle> candles = factory.constroiCandles(negociacoes);
		
		//como as negociacoes estao em 3 datas diferente deveriam existir, em teoria, 3 elementos na lista de candlesticks
		assertEquals(3, candles.size(), 0.00001);
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}
}