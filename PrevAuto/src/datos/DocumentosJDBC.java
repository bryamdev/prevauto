package datos;

import java.sql.*;
import java.util.*;

import domain.Documento;

public class DocumentosJDBC {
	
private static final String SQL_SELECT = "SELECT id_documento, fecha_expedicion, fecha_vencimiento, "
		+ "tipo_documento FROM pa.documento WHERE vehiculo_id = ?";
	
	//Toma todos los documentos de un vehiculo de la base de datos
	//Devuelve una lista con objetos de tipo Documento
	public static List<Documento> selectDocumentos(int vehiculoId){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Documento documento = null;
		List<Documento> documentos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_SELECT);
			stmt.setInt(1, vehiculoId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				documento = new Documento(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4));
				
				documentos.add(documento);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		return documentos;
		
	}
}
