package domain;

public class Vehiculo {
	
	private int idVehiculo;
	private String nombre;
	private String modelo;
	private String marca;
	private String placa;
	private int usuarioId;
	private String urlFoto;
	
	
	public Vehiculo() {
		
	}
	

	public int getIdVehiculo() {
		return idVehiculo;
	}


	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}


	public int getUsuarioId() {
		return usuarioId;
	}


	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}


	@Override
	public String toString() {
		return "Vehiculo: {idVehiculo=" + idVehiculo + ", nombre=" + nombre + ", modelo=" + modelo + ", marca=" + marca
				+ ", placa=" + placa + ", usuarioId=" + usuarioId + ", urlFoto=" + urlFoto + "}";
	}
	
		
}
