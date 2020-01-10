package servicios.configuracion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.TransformacionObjetos;
import datos.ConfiguracionJDBC;
import domain.*;

/**
 * Servlet implementation class ActualizarConfiguracion
 */
@WebServlet("/usuario/configuracion/actualizar")
public class ActualizarConfiguracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ActualizarConfiguracion() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int valor = Integer.parseInt(request.getParameter("valor"));
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Configuracion conf = new Configuracion();
		conf.setValor(valor);
		conf.setUsuarioId(idUsuario);
		
		Response res = ConfiguracionJDBC.updateConfiguracion(conf);
		String json = TransformacionObjetos.obtenerJson(res);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		
		
	}

}
