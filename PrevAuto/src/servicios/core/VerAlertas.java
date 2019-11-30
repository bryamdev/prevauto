package servicios.core;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.AlertasJDBC;
import domain.Alerta;

@WebServlet("/VerAlertas")
public class VerAlertas extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		List<Alerta> listaAlertas = AlertasJDBC.selectAlertas(idUsuario);
		String json = TransformacionObjetos.obtenerJson(listaAlertas);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.flush();
	}

}
