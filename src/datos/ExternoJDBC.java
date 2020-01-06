package datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Externo;


public class ExternoJDBC {

	private static final String SQL_SELECT = "SELECT evento_externo.nombre,"
			+ " evento_externo.fecha_evento, tipo_evento.nombre, evento_externo.descripcion"
			+ " FROM evento_externo, tipo_evento WHERE"
			+ " evento_externo.tipo_evento_id = tipo_evento.id_tipo_evento"
			+ " ORDER BY evento_externo.fecha_evento;";
	
	public static List<Externo> selectExternos(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Externo externo = null;
		List<Externo> externos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			//pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				externo = new Externo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				externos.add(externo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return externos;
		
	}
		
		
}

