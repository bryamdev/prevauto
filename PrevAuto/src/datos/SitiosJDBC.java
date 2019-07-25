package datos;

import java.sql.*;
import java.util.*;
import domain.Sitio;

public class SitiosJDBC {
	
	private final String SQL_SELECT = "SELECT * FROM sitio ";
	
	//Toma todos los sitios de la base de datos
	//Devuelve una lista con objetos de tipo Sitio
	public List<Sitio> selectSitios(){
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
				sitio = new Sitio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getLong(7), rs.getString(8), 
						rs.getString(9));
				
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
