package servicios.vehiculo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.VehiculosJDBC;
import domain.*;


@WebServlet("/vehiculo/eliminar")
public class EliminarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EliminarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
		Response res = VehiculosJDBC.deleteVehiculo(idVehiculo);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
		
		
		
	}

}
