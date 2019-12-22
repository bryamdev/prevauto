package servicios.vehiculo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.VehiculosJDBC;
import domain.*;

/**
 * Servlet implementation class RegistrarVehiculo
 */
@WebServlet("/vehiculo/registrar")
public class RegistrarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cookie[] cookies = request.getCookies();

		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		/*for(Cookie c : cookies) {
			if(c.getName().equals("idUsuario")) {
				idUsuario = Integer.parseInt(c.getValue());
			}
		}
		*/
		
		String nombre = request.getParameter("nombre");
		String modelo = request.getParameter("modelo");
		String marca = request.getParameter("marca");
		String placa = request.getParameter("placa");
		//String urlFoto = request.getParameter("urlFoto");
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setNombre(nombre);
		vehiculo.setModelo(modelo);
		vehiculo.setMarca(marca);
		vehiculo.setPlaca(placa);
		vehiculo.setUsuarioId(idUsuario);
		//vehiculo.setUrlFoto(urlFoto);
		
		Response res = VehiculosJDBC.insertVehiculo(vehiculo);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		
		
		
		
		
		
	}


}
