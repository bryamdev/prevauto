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
import domain.Usuario;

/**
 * Servlet implementation class ActualizarUsuario
 */
@WebServlet("/usuario/actualizar")
public class ActualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contraseña = request.getParameter("password");
		Long cedula = Long.parseLong(request.getParameter("cedula"));
		Long telefono = Long.parseLong(request.getParameter("telefono"));
		String urlFoto = request.getParameter("urlFoto");
		
		Usuario usuario = new Usuario(idUsuario, nombre, apellido, correo, contraseña
				, cedula, telefono, urlFoto);
		
		Response res = UsuariosJDBC.updateUsuario(usuario);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		
		
	}


}
