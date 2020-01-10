package datos;

import java.sql.*;

import domain.*;

public class ConfiguracionJDBC {
	
	private static final String SQL_SELECT = "SELECT configuracion.id_config, configuracion.tipo_config_id, "
			+ " tipo_config.nombre, configuracion.valor, configuracion.usuario_id "
			+ " FROM configuracion, tipo_config "
			+ " WHERE configuracion.tipo_config_id = tipo_config.id_tipo "
			+ " AND usuario_id = ?;";
	
	private static final String SQL_UPDATE = "UPDATE configuracion SET valor = ? WHERE usuario_id = ?;";
	
	private static final String SQL_INSERT_AFTER_INSERT_USUARIO = "INSERT INTO configuracion "
			+ " (tipo_config_id, valor, usuario_id) VALUES (1, 10, ?);";
	
	private static final String SQL_DELETE_BEFORE_DELETE_USUARIO = "DELETE FROM configuracion "
			+ " WHERE configuracion.usuario_id = ?; ";
	
	
	public static Configuracion selectConfiguracion(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Configuracion conf = null;
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, idUsuario);
			rs = pstmt.executeQuery();
			rs.next();
			
			conf = new Configuracion();
			conf.setIdConfig(rs.getInt(1));
			conf.setTipoConfigId(rs.getInt(2));
			conf.setTipoConfigNombre(rs.getString(3));
			conf.setValor(rs.getInt(4));
			conf.setUsuarioId(rs.getInt(5));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return conf;
		
	}
	
	public static Response updateConfiguracion(Configuracion conf) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, conf.getValor());
			pstmt.setInt(2, conf.getUsuarioId());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("La configuracion se actualizo correctamente!");
			}else {
				response.setMensaje("La configuracion NO se actualizó!");
				response.setError(true);
			}
			
		}catch(Exception e) {
			response.setMensaje("Error al actualizar la configuracion: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return response;
		
	}
	
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
