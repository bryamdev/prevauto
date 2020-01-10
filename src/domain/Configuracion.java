package domain;

public class Configuracion {
	
	private int idConfig;
	private int tipoConfigId;
	private String tipoConfigNombre;
	private int valor;
	private int usuarioId;
	
	public Configuracion() {
		
	}

	public int getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(int idConfig) {
		this.idConfig = idConfig;
	}

	public int getTipoConfigId() {
		return tipoConfigId;
	}

	public void setTipoConfigId(int tipoConfigId) {
		this.tipoConfigId = tipoConfigId;
	}

	public String getTipoConfigNombre() {
		return tipoConfigNombre;
	}

	public void setTipoConfigNombre(String tipoConfigNombre) {
		this.tipoConfigNombre = tipoConfigNombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "Configuracion: {idConfig=" + idConfig + ", tipoConfigId=" + tipoConfigId + ", tipoConfigNombre="
				+ tipoConfigNombre + ", valor=" + valor + ", usuarioId=" + usuarioId + "}";
	}

	
	
	

}
