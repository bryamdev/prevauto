package core;

import java.util.List;
import com.google.gson.Gson;
import datos.*;
import domain.*;

public class TransformacionObjetos {
	
	static Gson gson = new Gson();
	
	//comentario de ejemplo
	
	//Toma la lista de Sitios y lo serializa a json
	//Devuelve un string con el json de los sitios
	public static String obtenerJson(Object lista) {
	
		String sitiosJson = gson.toJson(lista);
	
		return sitiosJson;
	}
	
	public static String obtenerJson(List<Object> lista) {
		
		String sitiosJson = gson.toJson(lista);
	
		return sitiosJson;
	}
	
	
	/*public String obtenerVehiculosJson(int idUsuario) {
		vehiculoJDBC = new VehiculosJDBC();
		List<Vehiculo> vehiculos = vehiculoJDBC.selectVehiculos(idUsuario);
		String vehiculosJson = gson.toJson(vehiculos);
		
		return vehiculosJson;
	}
	
	public String obtenerDocumentosJson(int vehiculoId) {
		documentosJDBC = new DocumentosJDBC();
		List<Documento> documentos = documentosJDBC.selectDocumentos(vehiculoId);
		String documentosJson = gson.toJson(documentos);
		
		return documentosJson;
	}*/
			
}
