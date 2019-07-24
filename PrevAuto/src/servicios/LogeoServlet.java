package servicios;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/LogeoServlet")
public class LogeoServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String corrreo = request.getParameter("correo");
		String contraseña = request.getParameter("contraseña");
		
		
		
	}

}
