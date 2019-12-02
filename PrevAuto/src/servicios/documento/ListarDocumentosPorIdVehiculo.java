package servicios.documento;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.DocumentosJDBC;
import domain.Documento;


@WebServlet("/vehiculo/documento/listarPorId")
public class ListarDocumentosPorIdVehiculo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo")); 
		
		List<Documento> documentos = DocumentosJDBC.selectDocumentos(idVehiculo);
		
		String json = TransformacionObjetos.obtenerJson(documentos);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
