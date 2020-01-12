package servicios.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.AlertasJDBC;
import domain.Response;

/**
 * Servlet implementation class ModificarAlertas
 */
@WebServlet("/core/actualizarAlertas")
public class ActualizarAlertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ActualizarAlertas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Response res = AlertasJDBC.modificarEstadoAlertas(idUsuario);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		
	}


}
