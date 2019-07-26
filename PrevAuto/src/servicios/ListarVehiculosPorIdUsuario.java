package servicios;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.VehiculosJDBC;
import domain.Vehiculo;

/**
 * Servlet implementation class ListarVehiculosServlet
 */
@WebServlet("/ListarVehiculosPorIdUsuario")
public class ListarVehiculosPorIdUsuario extends HttpServlet {

	TransformacionObjetos transformacion = new TransformacionObjetos();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int idUsuarioResp = 2;
		
		List<Vehiculo> listaVehiculos = VehiculosJDBC.selectVehiculos(idUsuarioResp);
				
		String json = TransformacionObjetos.obtenerJson(listaVehiculos);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(json);
		out.flush();
	}

}
