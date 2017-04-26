package br.com.caelum.argentum.modelo;
/* *
 * GeradorAleatorioDeXML apenas gera XML com dados aleatorios para fins de teste 
 * */
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeradorAleatorioDeXML {

	public static void main(String[] args) throws IOException {
		Calendar data = Calendar.getInstance();
		//Random(123) configura o estado inicial do Random
		//Random() gera um Random aleatorio sem definição de estado inicial
		Random random = new Random(123);
		List<Negociacao> negociacoes = new ArrayList<Negociacao>();
		
		double valor = 40;
		int quantidade = 1000;
		
		for(int dias = 0; dias < 30; dias++) {//Um Candlestick para cada dia do mes
			int quantidadeNegociacoesDoDia = random.nextInt(20);//gera uma quantidade de dias entre 0 e 19
			//Gera n negociacoes no dia atual
			for(int negociacao = 0; negociacao < quantidadeNegociacoesDoDia; negociacao++) {
				valor += (random.nextInt(200) * 100) / 100;//variação do valores da negociações do dia
				if(valor < 5.0) {//Se o valor for muito baixo, fica congelado em 5.0
					valor = 5.0;
				}
				
				quantidade += 1000 % (random.nextInt(500) + 1);//variação de quanridades de cada negociação
				
				Negociacao n = new Negociacao(valor, quantidade, data);//cria a negociação com os dados gerados
				negociacoes.add(n);//adiciona a negociação na lista de negociações
			}
			//Incrementa a data em 1 dia
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		XStream stream = new XStream(new DomDriver());//Cria o XStream com o DomDriver para criar/ler XML
		stream.alias("negociacao", Negociacao.class);
		stream.setMode(XStream.NO_REFERENCES);//Indica para o XStream não criar tags identicas
		
		//PrintStream tem a habilidade de criar uma corrente de saida
		//PrintStream pode usar um arquivo como destino desta corrente de saida
		PrintStream out = new PrintStream(new File("negociacao.xml"));
		//O metodo println() recebe um Object, esse Object sera o objeto a ser escrito
		//no caso é a lista de nogociacoes convertida em uma estrutura XML
		out.println(stream.toXML(negociacoes));
	}

}
