package br.com.caelum.argentum.ws;

import static org.junit.Assert.*;
import br.com.caelum.argentum.modelo.Negociacao;
import org.junit.Test;
import java.util.List;

public class ClienteWebServiceTest {

	@Test
	public void getNegociacoesRetornaListaNaoNulaDeNegociacoes() {
		ClienteWebService client = new ClienteWebService();
		List<Negociacao> negociacoes = client.getNegociacoes();
		//System.out.println(negociacoes.size());
		assertFalse(negociacoes.size() == 0);
	}
}
