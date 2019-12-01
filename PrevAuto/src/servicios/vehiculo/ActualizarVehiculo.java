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

/**
 * Servlet implementation class ActualizarVehiculo
 */
@WebServlet("/vehiculo/actualizar")
public class ActualizarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ActualizarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
		String nombre = request.getParameter("nombre");
		String modelo = request.getParameter("modelo");
		String marca = request.getParameter("marca");
		String placa = request.getParameter("placa");
		String urlFoto = request.getParameter("urlFoto");
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(idVehiculo);
		vehiculo.setNombre(nombre);
		vehiculo.setModelo(modelo);
		vehiculo.setMarca(marca);
		vehiculo.setPlaca(placa);
		vehiculo.setUrlFoto(urlFoto);
		
		Response res = VehiculosJDBC.updateVehiculo(vehiculo);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
		
		
	}



}
