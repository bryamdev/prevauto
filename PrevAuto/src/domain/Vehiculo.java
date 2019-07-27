package domain;

public class Vehiculo {
	
	private int idVehiculo;
	private String nombre;
	private String modelo;
	private String marca;
	private String placa;
	private String url_foto;
	
	
	public Vehiculo(int idVehiculo, String nombre, String url_foto) {
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.url_foto = url_foto;
	}


	public Vehiculo(int idVehiculo, String nombre, String modelo, String marca, String placa,
			String url_foto) {
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.url_foto = url_foto;
	}
	
	
	
	
}
