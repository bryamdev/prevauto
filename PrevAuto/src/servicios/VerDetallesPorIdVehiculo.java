package servicios;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.VerDetallesVehiculoJDBC;
import domain.VerDetalles;


@WebServlet("/VerDetallesPorIdVehiculo")
public class VerDetallesPorIdVehiculo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculoReq = Integer.parseInt(request.getParameter("idVehiculo"));
		
		List<VerDetalles> listaDetalles = VerDetallesVehiculoJDBC.selectVehiculos(idVehiculoReq);
		
		String json = TransformacionObjetos.obtenerJson(listaDetalles);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.flush();
	}

}
