package domain;

import java.util.Date;

public class Documento {
	
	
	
	private int numero;
	private String fechaExpedicion;
	private String fechaVencimiento;
	private int vehiculoId;
	private String tipoDocumento;
	
	//atributos agregados para el servicio de verCronograma
	private String nombreVehiculo;
	
	
	
	public Documento(String tipoDocumento, int numero, String fechaExpedicion,
			String fechaVencimiento) {
		this.tipoDocumento = tipoDocumento;
		this.numero = numero;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		
	}
	
	public Documento(String fechaVencimiento, String nombreVehiculo, String tipoDocumento) {
		this.fechaVencimiento = fechaVencimiento;
		this.nombreVehiculo = nombreVehiculo;
		this.tipoDocumento = tipoDocumento;
		
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	

	public int getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(int vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public String getNombreVehiculo() {
		return nombreVehiculo;
	}

	public void setNombreVehiculo(String nombreVehiculo) {
		this.nombreVehiculo = nombreVehiculo;
	}
	
	
	
	
}
