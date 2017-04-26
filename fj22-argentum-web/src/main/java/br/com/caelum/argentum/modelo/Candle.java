package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Candle {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candle(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {
		if(maximo < minimo) {
			throw new IllegalArgumentException("Maximo nao pode ser menor que Minimo");
		}
		if(data == null) {
			throw new IllegalArgumentException("Data nao pode ser nula");
		}
		if(abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0 || volume < 0) {
			throw new IllegalArgumentException("Nao sao aceitos valores negativos");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}	
	
	//Quando a abertura é igual a fechamento, o Candlestick é considerado de alta
	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;		
	}
}
