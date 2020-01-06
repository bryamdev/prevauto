package domain;

public class Sitio {
	
	private String nombre;
	private String tipoNombre;
	private String horario;
	private String descripcion;
	private long telefono;
	private String direccion;
	private String urlFoto;
	
	public Sitio() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoNombre() {
		return tipoNombre;
	}

	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	@Override
	public String toString() {
		return "Sitio: {nombre=" + nombre + ", tipoNombre=" + tipoNombre + ", horario=" + horario + ", descripcion=" + descripcion
				+ ", telefono=" + telefono + ", direccion=" + direccion + ", urlFoto=" + urlFoto + "}";
	}
	
	
	

}
