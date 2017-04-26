package br.com.caelum.argentum.modelo;
/* *
 * CandleFactory tem a responsabilidade de criar Candles recebendo uma data e uma lista de negociacoes
 * */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CandleFactory {
	//Cria um unico Candle para um conjunto de negociacoes
	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		/*'maximo' e 'minimo' são configurados com esses valores para o caso de a lista de negociações estar vazia
		 * eles materem esses 'valores padrões'. Caso a lista NÃO esteja vazia eles são facilmente substituidos*/
		double maximo = 0;
		double minimo = Double.MAX_VALUE;
		
		double volume = 0;
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();			
			double preco = negociacao.getPreco();
			//Se o preço da negociação atual é maior que maximo, a negociação atual se torna o novo maximo
			if(preco > maximo) {
				maximo = preco;
			//Se o preco da negociação atual é menor que minimo, a negociação atual se torna o novo minimo
			}
			if(preco < minimo) {
				minimo = preco;
			}
		}//ATENÇÃO: no teste condicional existe um erro de logica, pois um preço pode ser o menor e o maior ao mesmo tempo(CORRIGIDO)
			
			//se a lista esta vazia configuramos 'abertura' como 0, caso contrario configuramos 'abertura' como o primeiro preço da lista
			double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
			//se a lista esta vazia configuramos 'fechamento' como 0, caso contrario configuramos 'fechamento' como o preço da ultima negociação
			double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();
			//se o preço minimo é Double.MAX_VALUE significa que ele não foi substituido, ou seja, não existem valores minimos, logo deve receber 0
			if(minimo == Double.MAX_VALUE) {
				minimo = 0;
			}
			
			//Ordenamos a lista de negociacoes antes de retorna-la
			Collections.sort(negociacoes);
			
			return new Candle(abertura, fechamento, minimo, maximo, volume, data);
		}

	//Separa todas as negociacoes em Candles por data
	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		//A lista de candlesticks a ser retornada.
		//Vamos adicionar o Candlestick de cada dia nela
		List<Candle> candles = new ArrayList<Candle>();
		
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();//Pega a primera negociacao do primeiro dia
		
		//Faz um looping atraves de todas as negociacoes
		for(Negociacao negociacao : todasNegociacoes) {			
			if(!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				//...criamos uma nova lista de negociacoes para outra data...
				negociacoesDoDia = new ArrayList<Negociacao>();
				//...e finalmente atualizamos a 'dataAtual' com a data da negociacao corrente
				dataAtual = negociacao.getData();
			}
			//1 - Se a negociacao atual é do mesmo dia da dataAtual da ultima negociacao...
			//2 - ... pôe esta negociacao na mesma lista que a negociacao anterior
			negociacoesDoDia.add(negociacao);
		}
		//Controi o ultimo Candlestick e adiciona a lista de Candlesticks
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles, List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
		candles.add(candleDoDia);
	}
}
