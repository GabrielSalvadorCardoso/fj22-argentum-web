package br.com.caelum.argentum.reader;
/* *
 * LeitorXML recebe o InputStream vindo de ClienteWebService e o converte em uma lista de negociações
 * */
import br.com.caelum.argentum.modelo.Negociacao;
import java.io.InputStream;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	//O objeto InputStream serve para recebermos dados externos ao programa
	public List<Negociacao> carrega(InputStream inputStream) {
		XStream stream = new XStream(new DomDriver());//DomDriver é a engine que gera/consome o XML
		
		//Cria um alias (Negociacao) para a classe Negociacao.
		//Se não usado, o XML será criado com <com br.com.caelum.argentum.modelo.Negociacao>
		stream.alias("negociacao", Negociacao.class);
		
		//fromXML recupera os dados de um arquivo XML
		//Para este XML <list> informa que o dado retornado pelo XML representa uma lista
		//Cada <negociacao> representa um objeto Negociacao desta lista
		//As tags dentro de cada negociacao representa um atributo do objeto Negociacao
		return (List<Negociacao>) stream.fromXML(inputStream);
	}
}
