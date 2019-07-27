package domain;

import java.util.*;

public class Cronograma {
	
	private List<Documento> documentos;
	private List<Externo> externos;
	
	public Cronograma(List<Documento> documentos, List<Externo> externos) {
		this.documentos = documentos;
		this.externos = externos;
		
	}

}
