package servicios;

import core.TransformacionObjetos;
import datos.SitiosJDBC;
import domain.Sitio;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/ListarSitiosRecomendados")
public class ListarSitiosRecomendados extends HttpServlet {
	
	
	//Obtiene la cadena json de sitios y lo envia como respuesta
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Sitio> listaSitios = SitiosJDBC.selectSitios();
		
		String json = TransformacionObjetos.obtenerJson(listaSitios);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(json);
		out.flush();
		
		
		
		
	}

}
