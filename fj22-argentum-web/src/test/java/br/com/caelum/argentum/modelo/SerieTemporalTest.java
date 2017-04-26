package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;
import org.junit.Test;

public class SerieTemporalTest {

	@Test(expected=IllegalArgumentException.class)
	public void serieTemporalNulaLancaIllegalStateException() {
		new SerieTemporal(null);
	}

}
