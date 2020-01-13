package servicios.vehiculo;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.VehiculosJDBC;
import domain.Vehiculo;



@WebServlet("/vehiculo/verDetallesPorId")
public class VerDetallesPorIdVehiculo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	  
    public VerDetallesPorIdVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
		
		Cookie cookieidVehiculo = new Cookie("idVehiculo", Integer.toString(idVehiculo));
		cookieidVehiculo.setPath("/");
		cookieidVehiculo.setMaxAge(300);
		response.addCookie(cookieidVehiculo);
		
		Vehiculo vehiculo = VehiculosJDBC.selectDetallesById(idVehiculo);		
		String json = TransformacionObjetos.obtenerJson(vehiculo);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.flush();
		
		
		
	}

}
