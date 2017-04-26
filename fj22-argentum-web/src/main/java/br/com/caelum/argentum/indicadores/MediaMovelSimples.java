package br.com.caelum.argentum.indicadores;
/* *
 * MediaMovelSimples recebe uma posição e uma SerieTemporal (que nada mais é do que uma lista de Candles)
 * Retorna uma media simples entre o Candle da posição indicada e os dois anteriores
 * */
import br.com.caelum.argentum.modelo.SerieTemporal;
public class MediaMovelSimples implements Indicador {
	private int diasParaCalculo;//'dias' é o numero de dias para entrar no calculo da media
	private Indicador outroIndicador;//Este indicador funciona como um seed para MediaMovelSimples
	
	public MediaMovelSimples(int dias, Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
		this.diasParaCalculo = dias;
	}

	//Este metodo recebe a posição do candle na lista e calcula a media entre este candle e os candles de dois dias anteriores a este
	//Os candles são buscados em SerieTemporal e são considerados apenas os valores de fechamento dos candles
	/* (non-Javadoc)
	 * @see br.com.caelum.argentum.indicadores.Indicador#calcula(int, br.com.caelum.argentum.modelo.SerieTemporal)
	 */
	@Override//Se informarmos uma posição inferior ao numero de dias passado no construtor haverá uma IndexOutOfBoundsException
	public double calcula(int posicao, SerieTemporal serie) {		
		double soma = 0.0;
		//a media é feita com o candle da posição indicada pela variavel 'posicao' e com os candles das dua posições anteriores a ele
		for(int i = posicao; i > posicao - diasParaCalculo; i--) {
			//soma += serie.getCandle(i).getFechamento(); //Equivalente ao codigo abaixo
			soma += outroIndicador.calcula(i, serie);
		}
		return (soma / 3);
	}
	
	@Override
	public String toString() {
		return "MMS " + this.outroIndicador.toString();//Seŕa o titulo da linha
	}
}
