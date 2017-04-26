package br.com.caelum.argentum.indicadores;
/* *
 * Todos os indicadores(MediaMovelSimple, MediaMovePonderada, MediaMovelFechamento, etc) devem implementar Indicador
 * */
import br.com.caelum.argentum.modelo.SerieTemporal;
public interface Indicador {

	public abstract double calcula(int posicao, SerieTemporal serie);

}