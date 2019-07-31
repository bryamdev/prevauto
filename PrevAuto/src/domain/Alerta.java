package domain;

import java.util.Date;

public class Alerta {
	
	private String tipoDocumento;
	private int numeroDocumento;
	private String nombreVehiculo;
	private String fechaVencimiento;
	
	
	public Alerta(String tipoDocumento, int numeroDocumento, String nombreVehiculo, 
			String fechaVencimiento) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombreVehiculo = nombreVehiculo;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	

}
