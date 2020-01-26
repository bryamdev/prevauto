package servicios.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class verFecha
 */
@WebServlet("/verFecha")
public class VerFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public VerFecha() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LocalDate fecha = LocalDate.now();
		String fechaTexto = fecha.toString();
		
		LocalTime hora = LocalTime.now();
		String horaTexto = hora.toString();
		
		Date date = new Date();
		
		
		PrintWriter out = response.getWriter();
		out.println(fechaTexto);
		out.println(horaTexto);
		out.println(date);
		
	}

}
