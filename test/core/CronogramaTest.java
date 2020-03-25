package core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datos.DocumentosJDBC;
import datos.ExternoJDBC;
import domain.*;

public class CronogramaTest {
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void selectDocumentosCroTest() {
		
		int idUsuario = 35;
		List<Documento> documentos = DocumentosJDBC.selectDocumentosCro(idUsuario);
		
		assertTrue(documentos.size()!=0);
		
	}
	
	@Test
	public void selectExternosCroTest() {
		
		List<Externo> externos = ExternoJDBC.selectExternos();
		
		assertTrue(externos.size()!=0);
		
	}

}
