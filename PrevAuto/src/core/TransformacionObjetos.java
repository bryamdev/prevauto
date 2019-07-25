package core;

import java.util.List;
import com.google.gson.Gson;
import datos.*;
import domain.*;

public class TransformacionObjetos {
	
	Gson gson = new Gson();
	SitiosJDBC sitioJDBC = null;
	VehiculosJDBC vehiculoJDBC = null;
	
	//Toma la lista de Sitios y lo serializa a json
	//Devuelve un string con el json de los sitios
	public String obtenerJsonSitios() {
	
		sitioJDBC = new SitiosJDBC();
		List<Sitio> sitios = sitioJDBC.selectSitios();
		String sitiosJson = gson.toJson(sitios);
	
		return sitiosJson;
	}
	
	
	public String obtenerVehiculosJson(int idUsuario) {
		vehiculoJDBC = new VehiculosJDBC();
		List<Vehiculo> vehiculos = vehiculoJDBC.selectVehiculos(idUsuario);
		String vehiculosJson = gson.toJson(vehiculos);
		
		return vehiculosJson;
	}
			
}
