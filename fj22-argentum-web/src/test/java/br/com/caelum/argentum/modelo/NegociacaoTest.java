package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.Assert;

public class NegociacaoTest {

	@Test//Testa a imutabilidade da data de uma negociacao
	public void dataDaNegociacaoEhImutavel() {
		//Cria uma data com mes 15
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		//Cria uma negociacao para esta data
		Negociacao n = new Negociacao(10, 5, c);
		
		//Tentamos mudar o mes da negociacao
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH), 0.00001);
	}
	
	//O teste deste metodo indica que deve ser lançada uma exceção caso o codigo dentro deste metodo seja executado
	//Se o codigo dentro deste metodo executar e não lançar IllegalArgumentException o teste foi reprovado
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test
	public void mesmoMilessegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoCalendar = (Calendar) agora.clone();
		Negociacao negociacao1 = new Negociacao(40.0, 100, agora);
		//Verifica se a negociacao é do mesmo dia que 'mesmoCalendar'
		assertTrue(negociacao1.isMesmoDia(mesmoCalendar));
	}
	
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		//GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao1 = new Negociacao(40.0, 10, manha);
		assertTrue(negociacao1.isMesmoDia(tarde));
	}
	
	@Test
	public void diasIguaisMasMesesDiferentesNaoSaoOMesmoDia() {
		Calendar primeiroJan = new GregorianCalendar(2011, 1, 1, 10, 0);
		Calendar primeiroFev = new GregorianCalendar(2011, 2, 1, 10, 0);
		Negociacao negociacao1 = new Negociacao(40.0, 10, primeiroJan);
		//Desta vez esperamos false como resultado pois dias iguais e meses diferentes representam datas dinstintas
		assertFalse(negociacao1.isMesmoDia(primeiroFev));
	}
	
	@Test
	public void diaEMesIguaisMasAnosDiferentesNaoSaoMesmaData() {
		Calendar ano1 = new GregorianCalendar(2010, 2, 10, 8, 0);
		Calendar ano2 = new GregorianCalendar(2011, 2, 10, 8, 0);
		Negociacao negociacao1 = new Negociacao(40.0, 10, ano1);
		assertFalse(negociacao1.isMesmoDia(ano2));
	}
}
