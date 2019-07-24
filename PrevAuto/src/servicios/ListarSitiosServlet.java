package servicios;

import core.TransformacionObjetos;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/ListarSitiosServlet")
public class ListarSitiosServlet extends HttpServlet {
	
	TransformacionObjetos transformacion = new TransformacionObjetos();
	//Obtiene la cadena json de sitios y lo envia como respuesta
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String json = transformacion.obtenerJsonSitios();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(json);
		out.flush();
		
	}

}
