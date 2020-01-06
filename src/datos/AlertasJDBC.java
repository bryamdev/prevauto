package datos;

import java.sql.*;
import java.util.*;

import domain.Alerta;
import domain.Response;

public class AlertasJDBC {
	
	private static final String SQL_SELECT = "SELECT tipo_documento.nombre, documento.numero, "
			+ " vehiculo.nombre, documento.fecha_vencimiento "
			+ " FROM alerta, documento, tipo_documento, vehiculo, usuario "
			+ " WHERE alerta.documento_id = documento.id_documento "
			+ " AND documento.vehiculo_id = vehiculo.id_vehiculo "
			+ " AND documento.tipo_documento = tipo_documento.id_tipo_documento "
			+ " AND vehiculo.usuario_id = usuario.id_usuario "
			+ " AND usuario.id_usuario = ? AND alerta.activo = 0; ";
	
	private static final String SQL_INSERT_AFTER_INSERT_DOCUMENTO = "INSERT INTO alerta(activo, documento_id) "
			+ " VALUES (0, ?);";
	
	private static final String SQL_DELETE_BEFORE_DELETE_DOCUMENTO = "DELETE FROM alerta "
			+ " WHERE documento_id = ?";
	
		
	//Toma todas las alertas de la base de datos
	//Devuelve una lista con objetos de tipo alertas
	public static List<Alerta> selectAlertas(int idUsuario){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Alerta> alertas = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_SELECT);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Alerta alerta = new Alerta();
				alerta.setTipoDocumento(rs.getString(1));
				alerta.setNumeroDocumento(rs.getLong(2));
				alerta.setNombreVehiculo(rs.getString(3));
				alerta.setFechaVencimiento(rs.getString(4));
				
				alertas.add(alerta);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		return alertas;
			
		}
	
	
	public static Response insertAfterInsertDocumento(int idDocumento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_AFTER_INSERT_DOCUMENTO);
			pstmt.setInt(1, idDocumento);
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("La alerta se guardó correctamente!");
			}else {
				response.setMensaje("La alerta NO se guardó!");
				response.setError(true);
			}
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar registrar la alerta: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;	
	}
	
	public static Response deleteBeforeDeleteDocumento(int idDocumento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_BEFORE_DELETE_DOCUMENTO);
			pstmt.setInt(1, idDocumento);
			
			int res = pstmt.executeUpdate();
			if(res != 0) {
				response.setMensaje("La alerta se eliminó correctamente!");
			}else{
				response.setMensaje("La alerta NO se eliminó!");
			}
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar elimnar las alertas: " + e.getMessage());
			response.setError(true);
			
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		
		
		
		return response;
		
	}
}
