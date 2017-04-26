package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.Test;
import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>"
								+ "<negociacao>"
									+ "<preco>43.5</preco>"
									+ "<quantidade>1000</quantidade>"
									+ "<data>"
										+ "<time>1322233344455</time>"
									+ "</data>"
								+ "</negociacao>"
						+ "</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		//getBytes() retorna a String em forma de bytes
		//ByteArrayInputStream converte bytes em um ImputStream
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		//carrega() retorna uma lista de negociacoes
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size(), 0.00001);//Verifica se a lista de negociacoes tem um elemento
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade(), 0.00001);
	}
	
	@Test
	public void listaDeNegociacoesVazia() {
		//Criar 'arquivo' XML
		String xmlDeTeste = "<list></list>";
		//Tranformar String em InputStream		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());		
		//Instanciar leitor
		LeitorXML leitor = new LeitorXML();
		//Envia 'arquivo' XML para o leitor e retorna uma lista de Negociacoes
		List<Negociacao> negociacoes = leitor.carrega(xml);
		//Verifica se a lista esta vazia
		assertEquals(0, negociacoes.size());
	}
	
	@Test
	public void semQuantidadeOuSemPreco() {
		String xmlDeTeste = "<list>"
								+ "<negociacao>"
									+ "<data>"
										+ "<time>1322233344455</time>"
									+ "</data>"
								+ "</negociacao>"
							+ "</list>";
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		LeitorXML leitor = new LeitorXML();
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		//assertEquals((double) 0.0, negociacoes.get(0).getPreco());
		assertEquals(0, negociacoes.get(0).getQuantidade());
	}
}
