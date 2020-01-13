package core;

import java.util.List;
import com.google.gson.Gson;

public class TransformacionObjetos {
	
	static Gson gson = new Gson();
	
	//comentario de ejemplo
	
	//Toma la lista de Sitios y lo serializa a json
	//Devuelve un string con el json de los sitios
	public static String obtenerJson(Object objeto) {
	
		String json = gson.toJson(objeto);
	
		return json;
	}
	
	public static String obtenerJson(List<Object> lista) {
		
		String json = gson.toJson(lista);
	
		return json;
	}
	

			
}
