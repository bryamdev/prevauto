package servicios;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.TransformacionObjetos;
import datos.DocumentosJDBC;
import domain.Documento;


@WebServlet("/ListarDocumentosPorIdVehiculo")
public class ListarDocumentosPorIdVehiculo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculoReq = Integer.parseInt(request.getParameter("idVehiculo")); 
		
		List<Documento> listaDocumentos = DocumentosJDBC.selectDocumentos(idVehiculoReq);
		
		String json = TransformacionObjetos.obtenerJson(listaDocumentos);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
