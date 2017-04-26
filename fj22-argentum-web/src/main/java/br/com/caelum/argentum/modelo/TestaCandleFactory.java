package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TestaCandleFactory {

	public static void main(String[] args) {
		//Calendar hoje = Calendar.getInstance();
		
		/*Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);*/
		
		/*Negociacao negociacao4 = new Negociacao(53.3, 100, hoje);
		Negociacao negociacao3 = new Negociacao(49.8, 100, hoje);			
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);//O Candlestick completo do dia
		
		System.out.println("Abertura: " + candle.getAbertura());
		System.out.println("Fechamento: " + candle.getFechamento());
		System.out.println("Minimo: " + candle.getMinimo());
		System.out.println("Maximo: " + candle.getMaximo());
		System.out.println("Volume: " + candle.getVolume());*/
		
		//TESTANDO ORDENAÇÃO
		Calendar agora = Calendar.getInstance();
		Negociacao n1 = new Negociacao(40.0, 100, agora);
		
		Calendar agoraMais1 = (Calendar) agora.clone();
		agoraMais1.add(Calendar.DAY_OF_MONTH, 1);
		Negociacao n2 = new Negociacao(50.0, 100, agoraMais1);
		
		Calendar agoraMais2 = (Calendar) agoraMais1.clone();
		agoraMais2.add(Calendar.DAY_OF_MONTH, 1);
		Negociacao n3 = new Negociacao(60.0, 100, agoraMais2);
		
		List<Negociacao> negociacoes = new ArrayList<Negociacao>();
		negociacoes.add(n2);
		negociacoes.add(n3);
		negociacoes.add(n1);
		
		System.out.println("ANTES DA ORDENAÇÃO");
		for(Negociacao negociacao : negociacoes) {
			System.out.println("Preco: " + negociacao.getPreco());
		}
		Collections.sort(negociacoes);
		System.out.println("\nDEPOIS DA ORDENAÇÃO");
		for(Negociacao negociacao : negociacoes) {
			System.out.println("Preco: " + negociacao.getPreco());
		}
	}

}
