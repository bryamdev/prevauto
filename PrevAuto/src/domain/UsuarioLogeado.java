package domain;

public class UsuarioLogeado {
	
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String urlFoto;
	private boolean error;
	
	public UsuarioLogeado(int idUsuario, String nombre, String apellido, String urlFoto, boolean error) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.urlFoto = urlFoto;
		this.error = error;
	}

}
