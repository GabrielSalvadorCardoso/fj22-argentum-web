package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandleFactorySemNegociacoes {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		List<Negociacao> negociacoes = Arrays.asList();
		CandleFactory factory = new CandleFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		System.out.println("Abertura: " + candle.getAbertura());
		System.out.println("Fechamento: " + candle.getFechamento());
		System.out.println("Minimo: " + candle.getMinimo());
		System.out.println("Maximo: " + candle.getMaximo());
		System.out.println("Volume: " + candle.getVolume());
	}
}
