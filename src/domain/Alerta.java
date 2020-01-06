package domain;



public class Alerta {
	
	private String tipoDocumento;
	private Long numeroDocumento;
	private String nombreVehiculo;
	private String fechaVencimiento;
	
	
	public Alerta() {
		
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public Long getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public String getNombreVehiculo() {
		return nombreVehiculo;
	}


	public void setNombreVehiculo(String nombreVehiculo) {
		this.nombreVehiculo = nombreVehiculo;
	}


	public String getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	@Override
	public String toString() {
		return "Alerta: {tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", nombreVehiculo="
				+ nombreVehiculo + ", fechaVencimiento=" + fechaVencimiento + "}";
	}
	
	
}
