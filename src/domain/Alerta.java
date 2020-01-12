package domain;



public class Alerta {
	
	private String tipoDocumentoNombre;
	private Long numeroDocumento;
	private String nombreVehiculo;
	private String fechaVencimiento;
	
	//atributo inicializado calculando direfencia de dias en tiempo de consulta
	private int diasRestantes;
	
	
	public Alerta() {
		
	}


	public String getTipoDocumentoNombre() {
		return tipoDocumentoNombre;
	}


	public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
		this.tipoDocumentoNombre = tipoDocumentoNombre;
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
	
	
	public int getDiasRestantes() {
		return diasRestantes;
	}


	public void setDiasRestantes(int diasRestantes) {
		this.diasRestantes = diasRestantes;
	}


	@Override
	public String toString() {
		return "Alerta: {tipoDocumentoNombre=" + tipoDocumentoNombre + ", numeroDocumento=" + numeroDocumento + ", nombreVehiculo="
				+ nombreVehiculo + ", fechaVencimiento=" + fechaVencimiento + ", diasRestantes=" + diasRestantes + "}";
	}

	
	
	
}
