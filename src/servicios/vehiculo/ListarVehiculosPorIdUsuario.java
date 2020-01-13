package servicios.vehiculo;

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
@WebServlet("/vehiculo/listarPorId")
public class ListarVehiculosPorIdUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	  
    public ListarVehiculosPorIdUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	TransformacionObjetos transformacion = new TransformacionObjetos();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		String url = request.getContextPath() + "/login.html";
		
		if(cookies == null) {
			//response.sendError(401);
			response.sendRedirect(url);
			
		}
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		
		List<Vehiculo> vehiculos = VehiculosJDBC.selectVehiculos(idUsuario);
				
		String json = TransformacionObjetos.obtenerJson(vehiculos);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(json);
	
	
	}

}
