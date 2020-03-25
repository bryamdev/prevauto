package core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datos.AlertasJDBC;
import domain.Alerta;

public class AlertasTest {

	
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void selectAlertasTest() {
		
		int idUsuario = 35;
		
		List<Alerta> alertas = AlertasJDBC.selectAlertas(idUsuario);
		
		assertTrue(alertas.size()!=0);
		
	}
	
	
	
	

}
