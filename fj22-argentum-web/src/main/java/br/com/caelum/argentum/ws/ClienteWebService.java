package br.com.caelum.argentum.ws;
/* *
 * ClientWebService tem a função de receber os dados de um WebService e repassa-los para LeitorXML em forma de InputStream
 * */
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//Esta classe é um WebClient que recebe dados do WebService localizado em http://argentumws.caelum.com.br/negociacoes
public class ClienteWebService {
	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";
	
	public List<Negociacao> getNegociacoes() {
		HttpURLConnection connection = null;
		
		try {
			URL url = new URL(URL_WEBSERVICE);//informa o local do WebService de onde vem os dados
			connection = (HttpURLConnection) url.openConnection();//Abre a requisição http para o WebService
			InputStream content = connection.getInputStream();//Retorna um InputStream dos dados para ser usado no leitor de XML
			return new LeitorXML().carrega(content);//recebe um InputStream com os dados e retorna uma lista de negociacoes
		} catch(IOException e) {//openConnection() e getInputStream() lançam um IOException
			throw new RuntimeException(e);
		} finally {
			//fecha a conexão com o WebService independentemente do sucesso ou fracasso do processo
			connection.disconnect();
		}				
	}
}
