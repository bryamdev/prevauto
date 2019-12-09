package servicios.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.*;
import domain.*;
import core.*;

/**
 * Servlet implementation class VerCronograma
 */
@WebServlet("/core/verCronograma")
public class VerCronograma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCronograma() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		List<Externo> externos = ExternoJDBC.selectExternos();
		List<Documento> documentos = DocumentosJDBC.selectDocumentosCro(idUsuario);
		
		Cronograma cronograma = new Cronograma(documentos, externos);
		String json = TransformacionObjetos.obtenerJson(cronograma);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		out.print(json);		
		out.flush();
		
	}

	

}
