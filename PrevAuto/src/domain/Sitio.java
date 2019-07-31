package domain;

public class Sitio {
	
	private String nombre;
	private String tipo;
	private String horario;
	private String descripcion;
	private long telefono;
	private String direccion;
	private String urlFoto;
	
	public Sitio(String nombre, String tipo, String horario, String descripcion, 
			long telefono, String direccion, String urlFoto) {
		
		this.nombre = nombre;
		this.tipo = tipo;
		this.horario = horario;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.urlFoto = urlFoto;
	}
	

}
