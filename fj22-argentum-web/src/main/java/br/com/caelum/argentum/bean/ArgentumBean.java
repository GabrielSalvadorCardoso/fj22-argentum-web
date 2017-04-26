package br.com.caelum.argentum.bean;
/* *
 *  ArgentumBean tem a função de se comunicar com o JSF passando as informações da lista de negociacoes
 * OBS: Aqui mostramos cada negociação idividualmente sem os Candles
 * */
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.ws.ClienteWebService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.ChartModel;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFactory;

//A anotação ViewScoped evita que fassamos uma nova requisição ao ClienteWebService a cada vez que mudamos de pagina
//A anotação ViewScoped indica que a instancia do ManagedBean deve durar enquanto a pagina que usa esta classe estiver ativa
//Quando estamos usando um escopo de dados(negociacoes) maior que o escopo de requisição(15 linha por pagina), o ManagedBean deve implementar Serializable
@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {	
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;
	
	public ArgentumBean() {
		//Armazena a lista de negociacoes retornada pelo ClienteWebService
		//Fazer isso no construtor evita chamadas desnecessarias ao ClienteWebService diminuindo o numero de requisições
		this.negociacoes = new ClienteWebService().getNegociacoes();
		gerarGrafico();
	}
	
	public void gerarGrafico() {
		System.out.println("PLOTADO: " + this.nomeMedia + " de " + this.nomeIndicadorBase);
		//Pega a lista de negociacao e monta os Candles
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		//Armazena a lista de candles em um SerieTemporal
		SerieTemporal serie = new SerieTemporal(candles);
		//SINTAXE: 1° parametro = SerieTemporal | 2° parametro = inicio | 3° parametro = fim
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
		//geradorGrafico.plotaIndicador(new MediaMovelSimples(3, new IndicadorFechamento()));//aqui o grafico é gerado de fato
		IndicadorFactory factory = new IndicadorFactory(this.nomeMedia, this.nomeIndicadorBase);
		geradorGrafico.plotaIndicador(factory.defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();//Retorna o grafico montado
	}//OBS: esperimente mudar a MediaMovel e o Indicador do metodo plotaIndicador() é veja as alterações no grafico
	

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}
	
	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}
}
