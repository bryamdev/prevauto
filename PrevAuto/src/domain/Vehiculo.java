package domain;

public class Vehiculo {
	
	private int idVehiculo;
	private String nombre;
	private String modelo;
	private String marca;
	private String placa;
	private String urlFoto;
	
	
	public Vehiculo(int idVehiculo, String nombre, String urlFoto) {
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.urlFoto = urlFoto;
	}


	public Vehiculo(int idVehiculo, String nombre, String modelo, String marca, String placa,
			String urlFoto) {
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.urlFoto = urlFoto;
	}
	
	
	
	
}
