package servicios;

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
@WebServlet("/VerCronograma")
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
		
		int id = 1;
		List<Externo> externos = ExternoJDBC.selectExternos(id);
		String json = TransformacionObjetos.obtenerJson(externos);
		
		List<Documento> documentos = DocumentosJDBC.selectDocumentosCro(id);
		String json2 = TransformacionObjetos.obtenerJson(documentos);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		//out.print(json);
		out.print(json2);
		out.print(documentos.size());
		
		
		out.flush();
		
	}

	

}
