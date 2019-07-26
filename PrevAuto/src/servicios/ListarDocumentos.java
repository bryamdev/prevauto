package servicios;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datos.DocumentosJDBC;


@WebServlet("/ListarDocumentos")
public class ListarDocumentos extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVehiculoReq = 1; 
		
		//List<Documento> listaDocumentos = DocumentosJDBC
		
		
	}

}
