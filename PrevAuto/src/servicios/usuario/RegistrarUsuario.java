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
import domain.*;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/usuario/registrar")
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("nombre").trim();
		String apellido = request.getParameter("apellido").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		Long cedula = Long.parseLong(request.getParameter("cedula"));
		Long telefono = Long.parseLong(request.getParameter("telefono"));
		
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setCedula(cedula);
		usuario.setTelefono(telefono);
		
		Response res = UsuariosJDBC.insertUsuario(usuario);
		
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		
		
	}

	

}
