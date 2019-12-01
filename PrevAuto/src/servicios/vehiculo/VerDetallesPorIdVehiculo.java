package servicios.vehiculo;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.VehiculosJDBC;
import domain.Vehiculo;



@WebServlet("/vehiculo/verDetallesPorId")
public class VerDetallesPorIdVehiculo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
		
		Vehiculo vehiculo = VehiculosJDBC.selectDetallesById(idVehiculo);
		
		String json = TransformacionObjetos.obtenerJson(vehiculo);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.println("TEST");
		out.flush();
	}

}
