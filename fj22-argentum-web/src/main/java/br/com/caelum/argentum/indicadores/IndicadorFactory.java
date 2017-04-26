package br.com.caelum.argentum.indicadores;
/* *
 * IndicadorFactory tem a responsabilidade de receber a media movel e o indicador base do usuario(advindos de ArgentumBean).
 * Com o tipo de Media Movel e o Indicador Base monta o indicador para ser usado para plotar o grafico
 * */
import java.lang.reflect.Constructor;

public class IndicadorFactory {
	private final String nomeMedia;
	private final String nomeIndicadorBase;
	
	public IndicadorFactory(String nomeMedia, String nomeIndicadorBase) {
		/*if(nomeMedia == null || nomeIndicadorBase == null) {
			throw new IllegalArgumentException();
		}*/
		this.nomeMedia = nomeMedia;
		this.nomeIndicadorBase = nomeIndicadorBase;
	}
	
	public Indicador defineIndicador() {
		//Se o usuario não escolheu o tipo de grafico, o padrão será um de media movel simples de fechamento
		if(this.nomeIndicadorBase == null || this.nomeMedia == null) {
			return new MediaMovelSimples(3, new IndicadorFechamento());
		}
		try {
			String pacote = "br.com.caelum.argentum.indicadores.";
			//Gera o indicador base de acordo com o item selecionado em 'selectOneButton' em index.xhtml'
			Class<?> classeIndicadorBase = Class.forName(pacote + this.nomeIndicadorBase);
			//Instancia a classe escolhida pelo usuario
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			//Gera a classe de media com base no item escolhido em 'selectOneButton' em index.xhtml
			Class<?> classeMedia = Class.forName(pacote + this.nomeMedia);
			//Aqui 'criamos' um construtor que recebe um Indicador(Este construtor deve existir na classe)
			Constructor<?> construtorMedia = classeMedia.getConstructor(int.class, Indicador.class);
			//Usamos o novo construtor criado para instanciar a classe(lembrando que o construtor recebe o numero de dias para calcular a media)
			Indicador indicador = (Indicador) construtorMedia.newInstance(3, indicadorBase);
			return indicador;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
