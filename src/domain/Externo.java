package domain;

public class Externo {
	
	private String nombre;
	private String fecha;
	private String tipoEvento;
	private String descripcion;
	
	public Externo(String nombre, String fecha, String tipoEvento, String descripcion) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.tipoEvento = tipoEvento;
		this.descripcion = descripcion;
		
	}

	@Override
	public String toString() {
		return "Externo [nombre=" + nombre + ", fecha=" + fecha + ", tipoEvento=" + tipoEvento + ", descripcion="
				+ descripcion + "]";
	}
	
	

}
