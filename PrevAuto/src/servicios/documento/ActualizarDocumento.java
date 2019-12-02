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
 * Servlet implementation class ActualizarDocumento
 */
@WebServlet("/vehiculo/documento/actualizar")
public class ActualizarDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ActualizarDocumento() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
		Long numero = Long.parseLong(request.getParameter("numero"));
		String fechaExpedicion = request.getParameter("fechaExpedicion");
		String fechaVencimiento = request.getParameter("fechaVencimiento");
		int tipoDocumento = Integer.parseInt(request.getParameter("tipoDocumento"));
		
		Documento documento = new Documento();
		documento.setIdDocumento(idDocumento);
		documento.setNumero(numero);
		documento.setFechaExpedicion(fechaExpedicion);
		documento.setFechaVencimiento(fechaVencimiento);
		documento.setTipoDocumento(tipoDocumento);
		
		Response res = DocumentosJDBC.updateDocumento(documento);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
		
		
	}


}
