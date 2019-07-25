package domain;

public class Sitio {
	
	private int sitioId;
	private String nombre;
	private int tipo;
	private int prioridad;
	private String horario;
	private String descripcion;
	private long telefono;
	private String direccion;
	private String urlFoto;
	
	public Sitio(int sitioId,String nombre, int tipo, int prioridad, 
			String horario, String descripcion, long telefono, String direccion, String urlFoto) {
		this.sitioId = sitioId;
		this.nombre = nombre;
		this.tipo = tipo;
		this.prioridad = prioridad;
		this.horario = horario;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.urlFoto = urlFoto;
	}
	

}
