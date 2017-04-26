package br.com.caelum.argentum.indicadores;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.indicadores.*;
public class MediaMovelPonderadaTest {

	@Test
	public void sequenciaSimplesDeCandles() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		Indicador indicador = new MediaMovelPonderada(3, new IndicadorFechamento());
		
		assertEquals(14.0/6, indicador.calcula(2, serie), 0.00001);
		assertEquals(20.0/6, indicador.calcula(3, serie), 0.00001);
		assertEquals(26.0/6, indicador.calcula(4, serie), 0.00001);
		assertEquals(32.0/6, indicador.calcula(5, serie), 0.00001);
	}
	
	//Neste teste o numero de dias para calcular(passado no construtor) é 5
	//Se dissermos que queremos começar o calculo da media do indice 3, não haverá numeros sufucientes
	//Então é lançado um IndexOutOfBoundException
	@Test(expected=IndexOutOfBoundsException.class)
	public void indicesMenorQueNumeroDeDiasNaoSaoCalculaveis() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		Indicador indicador = new MediaMovelPonderada(5, new IndicadorFechamento());
		
		indicador.calcula(3, serie);
	}
}
