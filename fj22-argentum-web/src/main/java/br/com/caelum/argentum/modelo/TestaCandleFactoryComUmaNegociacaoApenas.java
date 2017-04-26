package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandleFactoryComUmaNegociacaoApenas {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();

		Negociacao negaciacao1 = new Negociacao(40.5, 100, hoje);
		List<Negociacao> negociacoes = Arrays.asList(negaciacao1);
		CandleFactory factory = new CandleFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		System.out.println("Abertura:\t" + candle.getAbertura());
		System.out.println("Fechamento:\t" + candle.getFechamento());
		System.out.println("Minimo:\t\t" + candle.getMinimo());
		System.out.println("Maximo:\t\t" + candle.getMaximo());
		System.out.println("Volume:\t\t" + candle.getVolume());
	}

}
