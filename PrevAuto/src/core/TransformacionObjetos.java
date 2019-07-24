package core;

import java.util.List;
import com.google.gson.Gson;
import datos.SitiosJDBC;
import domain.Sitio;

public class TransformacionObjetos {
	
	SitiosJDBC sitioJDBC = new SitiosJDBC();
	//Toma la lista de Sitios y lo serializa a json
	//Devuelve un string con el json de los sitios
	public String obtenerJsonSitios() {
	
		List<Sitio> sitios = sitioJDBC.selectSitios();
		Gson gson = new Gson();
		String sitiosJson = gson.toJson(sitios);
	
		return sitiosJson;
	}
			
}
