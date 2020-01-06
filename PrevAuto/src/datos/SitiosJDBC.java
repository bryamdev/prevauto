package datos;

import java.sql.*;
import java.util.*;
import domain.Sitio;

public class SitiosJDBC {
	
	private static final String SQL_SELECT = "SELECT sitio.nombre, tipo_sitio.nombre, "
			+ " sitio.horario, sitio.descripcion, sitio.telefono, sitio.direccion, sitio.url_foto "
			+ " FROM sitio, tipo_sitio WHERE sitio.tipo = tipo_sitio.id_tipo_sitio "
			+ " ORDER BY sitio.prioridad; ";
	
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
				sitio = new Sitio();
				sitio.setNombre(rs.getString(1));
				sitio.setTipoNombre(rs.getString(2));
				sitio.setHorario(rs.getString(3));
				sitio.setDescripcion(rs.getString(4));
				sitio.setTelefono(rs.getLong(5));
				sitio.setDireccion(rs.getString(6));
				sitio.setUrlFoto(rs.getString(7));
				
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
