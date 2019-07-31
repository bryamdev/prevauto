package datos;

import java.sql.*;
import java.util.*;
import domain.Sitio;

public class SitiosJDBC {
	
	private static final String SQL_SELECT = "SELECT pa.sitio.nombre, pa.tipo_sitio.nombre, "
			+ "pa.sitio.horario, pa.sitio.descripcion, pa.sitio.telefono, pa.sitio.direccion, "
			+ "pa.sitio.url_foto FROM pa.sitio, pa.tipo_sitio WHERE pa.sitio.tipo = "
			+ "pa.tipo_sitio.idtipo_sitios ORDER BY pa.sitio.prioridad";
	
	//Toma todos los sitios de la base de datos
	//Devuelve una lista con objetos de tipo Sitio
	public static List<Sitio> selectSitios(){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Sitio sitio = null;
		List<Sitio> sitios = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				sitio = new Sitio(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)
						, rs.getLong(5), rs.getString(6), rs.getString(7));
				
				sitios.add(sitio);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		return sitios;
		
	}
}
