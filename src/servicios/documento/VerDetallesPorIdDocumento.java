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
import domain.*;

/**
 * Servlet implementation class VerDetallesPorIdDocumento
 */
@WebServlet("/vehiculo/documento/verDetallesPorId")
public class VerDetallesPorIdDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerDetallesPorIdDocumento() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
		
		Documento documento = DocumentosJDBC.selectDetallesById(idDocumento);
		String json = TransformacionObjetos.obtenerJson(documento);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
	}


}
