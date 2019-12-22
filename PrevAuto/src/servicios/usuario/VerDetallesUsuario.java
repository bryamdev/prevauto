package servicios.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.UsuariosJDBC;
import domain.Usuario;

/**
 * Servlet implementation class VerDetallesPorIdUsuario
 */
@WebServlet("/usuario/verDetalles")
public class VerDetallesUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerDetallesUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		Cookie[] cookies = request.getCookies();
		
		int idUsuario = 0;
		
		if(cookies != null) {
			
			idUsuario = Integer.parseInt(cookies[0].getValue());
		}else {
			idUsuario = Integer.parseInt(request.getParameter("idUsuario")); 
		}
		*/
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Usuario usuario = UsuariosJDBC.selectUsuario(idUsuario);
		
		String json = TransformacionObjetos.obtenerJson(usuario);
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		
		
	}

	

}
