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
import domain.Configuracion;

/**
 * Servlet implementation class VerConfiguracionUsuario
 */
@WebServlet("/usuario/configuracion/verPorId")
public class VerConfiguracionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VerConfiguracionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Configuracion conf = ConfiguracionJDBC.selectConfiguracion(idUsuario);
		String json = TransformacionObjetos.obtenerJson(conf);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		
		
	}

	

}
