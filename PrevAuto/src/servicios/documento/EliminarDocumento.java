package servicios.documento;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.DocumentosJDBC;
import domain.Response;


/**
 * Servlet implementation class EliminarDocumento
 */
@WebServlet("/vehiculo/documento/eliminar")
public class EliminarDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public EliminarDocumento() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
		
		Response res = DocumentosJDBC.deleteDocumento(idDocumento);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
		
		
	}


}
