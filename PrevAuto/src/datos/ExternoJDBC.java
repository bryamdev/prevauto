package datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Externo;
import datos.*;

public class ExternoJDBC {

	private static final String SQL_SELECT = "SELECT pa.evento_externo.nombre,"
			+ " pa.evento_externo.fecha_evento, pa.tipo_evento.nombre, pa.evento_externo.descripcion"
			+ " FROM pa.evento_externo, pa.tipo_evento where id_evento_externo = ? AND"
			+ " pa.evento_externo.tipo_evento_id=pa.tipo_evento.id_tipo_evento;";
	
	public static List<Externo> selectSitios(int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Externo externo = null;
		List<Externo> externos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, id);
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

