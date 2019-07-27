package domain;

import java.util.Date;

public class Documento {
	
	
	private String tipo_documento;
	private int numero;
	private Date fechaExpedicion;
	private Date fechaVencimiento;
	//atributos agregados para el servicio de verCronograma
	private String nombreVehiculo;
	
	
	
	public Documento(String tipo_documento, int numero, Date fechaExpedicion,
			Date fechaVencimiento) {
		this.tipo_documento = tipo_documento;
		this.numero = numero;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		
	}
	
	public Documento(Date fechaVencimiento, String nombreVehiculo, String tipo_documento) {
		this.fechaVencimiento = fechaVencimiento;
		this.nombreVehiculo = nombreVehiculo;
		this.tipo_documento = tipo_documento;
		
	}
	
	
}
