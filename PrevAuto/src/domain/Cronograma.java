package domain;

import java.util.*;

public class Cronograma {
	
	private List<Documento> documentos;
	private List<Externo> externos;
	
	public Cronograma() {
		
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Externo> getExternos() {
		return externos;
	}

	public void setExternos(List<Externo> externos) {
		this.externos = externos;
	}

	@Override
	public String toString() {
		return "Cronograma: {documentos=" + documentos + ", externos=" + externos + "}";
	}
	

}
