package domain;

import java.util.Date;

public class Alerta {
	
	private String tipoDocumento;
	private Long numeroDocumento;
	private String nombreVehiculo;
	private String fechaVencimiento;
	
	
	public Alerta(String tipoDocumento, Long numeroDocumento, String nombreVehiculo, 
			String fechaVencimiento) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombreVehiculo = nombreVehiculo;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	

}
