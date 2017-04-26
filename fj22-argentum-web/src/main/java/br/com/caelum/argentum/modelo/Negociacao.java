package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.io.Serializable;

//A classe cujos dados são utilizadas por um ManagedBean que usa paginação, precisa implementar Serializable
//No caso, esta classe esta sendo usada por ArgentumBean, que é um @ManagedBean
public final class Negociacao implements Comparable<Negociacao>, Serializable  {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(double preco, int quantidade, Calendar data) {
		if(data == null) {//Impede a criação de nogociacoes com data nula
			throw new IllegalArgumentException("Data nao pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}
	
	//Comparação para ordenação por data
	@Override
	public int compareTo(Negociacao negociacao2) {
		if(this.getData().getTimeInMillis() < negociacao2.getData().getTimeInMillis()) {
			return -1;
		}
		if(this.getData().getTimeInMillis() > negociacao2.getData().getTimeInMillis()) {
			return 1;
		}
		return 0;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	//Retornamos um clone da data e não a data em si. Isso evita que a data original seja alterada. Tornando assim, a data imutavel
	public Calendar getData() {		
		return (Calendar) this.data.clone();
	}
	
	public double getVolume() {
		return preco * quantidade;
	}

	//Verifica se a data desta negociação é igual a data passada com argumento
	public boolean isMesmoDia(Calendar outraData) {
		return this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH)
				&& this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}
}
