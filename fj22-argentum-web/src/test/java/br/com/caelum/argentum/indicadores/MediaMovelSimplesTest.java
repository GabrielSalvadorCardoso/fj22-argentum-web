package br.com.caelum.argentum.indicadores;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandles() throws Exception {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples(3, new IndicadorFechamento());
		
		assertEquals(2.0, mms.calcula(2, serie), 0.00001);//indice 2 = 3° dia
		assertEquals(3.0, mms.calcula(3, serie), 0.00001);//(4° dia + 3° dia + 2° dia) / 3 = 3.0
		assertEquals(10.0/3, mms.calcula(4, serie), 0.00001);
		assertEquals(11.0/3, mms.calcula(5, serie), 0.00001);
		assertEquals(4.0, mms.calcula(6, serie), 0.00001);
		assertEquals(13.0/3, mms.calcula(7, serie), 0.00001);
		assertEquals(4.0, mms.calcula(8, serie), 0.00001);
	}
	
	@Test
	public void sequenciaSimplesDeCandlesAbertura() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples(3, new IndicadorAbertura());
		assertEquals(2.0, mms.calcula(2, serie), 0.00001);
		assertEquals(3.0, mms.calcula(3, serie), 0.00001);
		assertEquals(10.0/3, mms.calcula(4, serie), 0.00001);
		assertEquals(11.0/3, mms.calcula(5, serie), 0.00001);
		assertEquals(4.0, mms.calcula(6, serie), 0.00001);
		assertEquals(13.0/3, mms.calcula(7, serie), 0.00001);
		assertEquals(4.0, mms.calcula(8, serie), 0.00001);
	}

	//Caso a posição passada não permitir o calculo da media entre 3 dias deve acontecer um IndexOutOfBoundsException
	//Exemplo se a media for feita entre os tres ultimos dias, a posição passada como parametro não pode ser < 2
	@Test(expected=IndexOutOfBoundsException.class)
	public void indicesMenoresQueNumeroDeDiasNaoSaoCalculaveis() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples(3, new IndicadorFechamento());
		mms.calcula(1, serie);
	}
}
