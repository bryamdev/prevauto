package domain;

public class Documento {
	
	
	private int idDocumento;
	private Long numero;
	private String fechaExpedicion;
	private String fechaVencimiento;
	private int vehiculoId;
	private int tipoDocumento;
	
	
	//atributos agregados para el servicio de verCronograma
	private String nombreVehiculo;
	private String tipoDocumentoNombre;
	
	
	public Documento() {
	}
	
	public int getIdDocumento() {
		return idDocumento;
	}


	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}


	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
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

	public String getTipoDocumentoNombre() {
		return tipoDocumentoNombre;
	}

	public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
		this.tipoDocumentoNombre = tipoDocumentoNombre;
	}

	@Override
	public String toString() {
		return "Documento: {idDocumento=" + idDocumento + ", numero=" + numero + ", fechaExpedicion=" + fechaExpedicion
				+ ", fechaVencimiento=" + fechaVencimiento + ", vehiculoId=" + vehiculoId + ", tipoDocumento="
				+ tipoDocumento + ", nombreVehiculo=" + nombreVehiculo + ", tipoDocumentoNombre=" + tipoDocumentoNombre
				+ "}";
	}
	
}
