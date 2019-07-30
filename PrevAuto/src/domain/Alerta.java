package domain;

import java.util.Date;

public class Alerta {
	
	private String tipoDocumento;
	private int numeroDocumento;
	private String nombreVehiculo;
	private Date fechaVencimiento;
	
	
	public Alerta(String tipoDocumento, int numeroDocumento, String nombreVehiculo, 
			Date fechaVencimiento) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombreVehiculo = nombreVehiculo;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	

}
