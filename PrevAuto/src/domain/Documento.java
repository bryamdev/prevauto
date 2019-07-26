package domain;

import java.util.Date;

public class Documento {
	
	private int idDocumento;
	private Date fechaExpedicion;
	private Date fechaVencimiento;
	private String tipo_documento;
	
	public Documento(int idDocumento, Date fechaExpedicion, Date fechaVencimiento, 
			String tipo_documento) {
		this.idDocumento = idDocumento;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		this.tipo_documento = tipo_documento;
	}
	
	
}
