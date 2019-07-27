package domain;

import java.util.Date;

public class Alerta {
	
	private String tipo_documento;
	private int numero_documento;
	private String nombre_vehiculo;
	private Date fecha_vencimiento;
	
	
	public Alerta(String tipo_documento, int numero_documento, String nombre_vehiculo, 
			Date fecha_vencimiento) {
		this.tipo_documento = tipo_documento;
		this.numero_documento = numero_documento;
		this.nombre_vehiculo = nombre_vehiculo;
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	
	

}
