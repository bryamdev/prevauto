package servicios.usuario;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.UsuariosJDBC;
import datos.ValidacionUsuarioJDBC;
import domain.*;


@WebServlet("/usuario/validar")
public class LogeoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Response res = ValidacionUsuarioJDBC.validarUsuario(email, password);
		String json = TransformacionObjetos.obtenerJson(res);
		
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		if(!res.isError()) {
			
			Usuario usuLogueado = UsuariosJDBC.selectUsuario(Integer.parseInt(res.getMensaje()));
			
			Cookie cookieIdUsuario = new Cookie("idUsuario", Integer.toString(usuLogueado.getIdUsuario()));
			cookieIdUsuario.setPath("/PrevAuto");
			cookieIdUsuario.setMaxAge(600);
			
			Cookie cookieNombreUsuario = new Cookie("nombre", usuLogueado.getNombre());
			cookieNombreUsuario.setPath("/PrevAuto");
			cookieNombreUsuario.setMaxAge(600);
			
			Cookie cookieEmailUsuario = new Cookie("email", usuLogueado.getEmail());
			cookieEmailUsuario.setPath("/PrevAuto");
			cookieEmailUsuario.setMaxAge(600);

			Cookie cookieUrlFoto = new Cookie("urlFoto", usuLogueado.getUrlFoto());
			cookieUrlFoto.setPath("/PrevAuto");
			cookieUrlFoto.setMaxAge(600);
			
			response.addCookie(cookieIdUsuario);
			response.addCookie(cookieNombreUsuario);
			response.addCookie(cookieEmailUsuario);
			response.addCookie(cookieUrlFoto);

		}
		
		
		out.println(json);
		
		
				
	}

}
