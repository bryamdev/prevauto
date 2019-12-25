package servicios.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.UsuariosJDBC;
import domain.Response;

/**
 * Servlet implementation class EliminarUsuario
 */
@WebServlet("/usuario/eliminar")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Response res = UsuariosJDBC.deleteUsuario(idUsuario);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		
		
	}

	

}
