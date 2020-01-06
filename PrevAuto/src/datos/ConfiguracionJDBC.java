package datos;

import java.sql.*;

import domain.Response;

public class ConfiguracionJDBC {
	
	private static final String SQL_INSERT_AFTER_INSERT_USUARIO = "INSERT INTO configuracion "
			+ " (tipo_config_id, valor, usuario_id) VALUES (1, 10, ?);";
	
	private static final String SQL_DELETE_BEFORE_DELETE_USUARIO = "DELETE FROM configuracion "
			+ " WHERE configuracion.usuario_id = ?; ";
	
	
	public static Response insertAfterInsertUsuario(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
			
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_AFTER_INSERT_USUARIO);
			pstmt.setInt(1, idUsuario);
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("La configuracion se guardó correctamente!");
			}else {
				response.setMensaje("El documento NO se guardó!");
				response.setError(true);
			}
			
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar registrar configuracion: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
		
	}
	
	public static Response DeleteBeforeDeleteUsuario(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
			
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_BEFORE_DELETE_USUARIO);
			pstmt.setInt(1, idUsuario);
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("La configuracion se eliminó correctamente!");
			}else {
				response.setMensaje("La configuracion NO se eliminó!");
			}
			
			
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar eliminar configuracion: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
		
	}

}
