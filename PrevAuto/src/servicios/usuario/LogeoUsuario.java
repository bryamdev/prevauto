package servicios.usuario;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.ValidacionUsuarioJDBC;
import domain.Response;


@WebServlet("/LogeoUsuario")
public class LogeoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		
		Response res = ValidacionUsuarioJDBC.validarUsuario(correo, password);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		
		if(!res.isError()) {
			Cookie cookie = new Cookie("idUsuario",res.getMensaje());
			response.addCookie(cookie);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
				
	}

}
