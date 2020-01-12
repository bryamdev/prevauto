package datos;

import java.sql.*;
import java.util.*;

import domain.*;

public class DocumentosJDBC {
	
	private static final String SQL_LISTAR = "SELECT documento.id_documento, "
			+ " tipo_documento.nombre, documento.numero, documento.fecha_expedicion, "
			+ " documento.fecha_vencimiento, documento.tipo_documento "
			+ " FROM documento, tipo_documento "
			+ " WHERE documento.vehiculo_id = ? "
			+ " AND documento.tipo_documento = tipo_documento.id_tipo_documento";

	private static final String SQL_SELECT_CRO = "SELECT documento.fecha_vencimiento, "
			+ " vehiculo.nombre, tipo_documento.nombre, documento.vehiculo_id, documento.id_documento "
			+ " FROM documento, vehiculo, tipo_documento, usuario "
			+ " WHERE usuario.id_usuario = vehiculo.usuario_id "  
			+ " AND vehiculo.id_vehiculo = documento.vehiculo_id " 
			+ " AND tipo_documento.id_tipo_documento = documento.tipo_documento "
			+ " AND usuario.id_usuario = ? ORDER BY documento.fecha_vencimiento; ";

	private static final String SQL_SELECT_BY_ID = "SELECT id_documento, numero, fecha_expedicion, "
			+ " fecha_vencimiento, tipo_documento FROM documento WHERE id_documento = ?; ";
	
	private static final String SQL_INSERT = "INSERT INTO documento(numero, fecha_expedicion, "
			+ " fecha_vencimiento, vehiculo_id, tipo_documento) VALUES (?, ?, ?, ?, ?); ";

	private static final String SQL_UPDATE = "UPDATE documento SET numero = ?, fecha_expedicion = ?,"
			+ " fecha_vencimiento = ?, tipo_documento = ? WHERE id_documento = ?; ";

	private static final String SQL_DELETE = "DELETE FROM documento WHERE id_documento = ?; ";
	
	private static final String SQL_SELECT_BY_VEHICULO_ID = "SELECT id_documento FROM documento "
			+ " WHERE vehiculo_id = ?"; 

	
	public static List<Documento> selectDocumentosCro(int idUsuario){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Documento documento = null;
		
		List<Documento> documentos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_CRO);
			pstmt.setInt(1, idUsuario);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				documento = new Documento();
				documento.setFechaVencimiento(rs.getString(1));
				documento.setNombreVehiculo(rs.getString(2));
				documento.setTipoDocumentoNombre(rs.getString(3));
				documento.setVehiculoId(rs.getInt(4));
				documento.setIdDocumento(rs.getInt(5));
				documentos.add(documento);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return documentos;
	}
	
	public static List<Documento> selectDocumentos(int vehiculoId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Documento documento = null;
		List<Documento> documentos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_LISTAR);
			pstmt.setInt(1, vehiculoId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				documento = new Documento();
				documento.setIdDocumento(rs.getInt(1));
				documento.setTipoDocumentoNombre(rs.getString(2));
				documento.setNumero(rs.getLong(3));
				documento.setFechaExpedicion(rs.getString(4));
				documento.setFechaVencimiento(rs.getString(5));
				documento.setTipoDocumento(rs.getInt(6));
				
				documentos.add(documento);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return documentos;
	}
	
	public static Documento selectDetallesById(int idDocumento){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Documento documento = null;
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, idDocumento);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				documento = new Documento();
				documento.setIdDocumento(rs.getInt(1));
				documento.setNumero(rs.getLong(2));
				documento.setFechaExpedicion(rs.getString(3));
				documento.setFechaVencimiento(rs.getString(4));
				documento.setTipoDocumento(rs.getInt(5));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return documento;	
	}
	
	public static Response insertDocumento(Documento documento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try{
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT);
			pstmt.setLong(1, documento.getNumero());
			pstmt.setString(2, documento.getFechaExpedicion());
			pstmt.setString(3, documento.getFechaVencimiento());
			pstmt.setInt(4, documento.getVehiculoId());
			pstmt.setInt(5, documento.getTipoDocumento());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				Response resTrigger = AlertasJDBC.insertAfterInsertDocumento(getMaxAutoincrement());
				if(!resTrigger.isError()) {
					response.setMensaje("El documento se guardó correctamente!");
				}else {
					response.setMensaje("El documnto se guardo pero NO la alerta");
				}
			}else {
				response.setMensaje("El documento NO se guardó!");
				response.setError(true);;
			}
						
		}catch(SQLException e) {
			response.setMensaje("Error al intentar registrar el documento: " + e.getMessage());
			response.setError(true);;
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		return response;
	}
	
	public static Response updateDocumento(Documento documento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try{
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setLong(1, documento.getNumero());
			pstmt.setString(2, documento.getFechaExpedicion());
			pstmt.setString(3, documento.getFechaVencimiento());
			pstmt.setInt(4, documento.getTipoDocumento());
			pstmt.setInt(5, documento.getIdDocumento());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("El documento se actualizó correctamente!");
			}else {
				response.setMensaje("El documento NO se actualizó!");
				response.setError(true);;
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar actualizar el documento: " + e.getMessage());
			response.setError(true);;
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		return response;
	}
	
	public static Response deleteDocumento(int idDocumento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try{
			Response resTrigger = AlertasJDBC.deleteBeforeDeleteDocumento(idDocumento);
			
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, idDocumento);
			
			if(!resTrigger.isError()) {
				
				int res = pstmt.executeUpdate();
				
				if(res != 0) {
					response.setMensaje("El documento se eliminó correctamente!");
				}else {
					response.setMensaje("El documento NO se eliminó!");
					response.setError(true);;
				}
			}else {
				response.setMensaje(resTrigger.getMensaje());
				response.setError(true);
			}
			
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar eliminar el documento: " + e.getMessage());
			response.setError(true);;
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		return response;
	}
	
	public static Response deleteDocumentoBeforeDeleteVehiculo(int idVehiculo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_VEHICULO_ID);
			pstmt.setInt(1, idVehiculo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				deleteDocumento(rs.getInt(1));
			}
			
			/*
			if(res != 0) {
				response.setMensaje("El documento se eliminó correctamente!");
			}else {
				response.setMensaje("El documento NO se eliminó!");
				response.setError(true);
			}
			
			*/
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar eliminar el documento: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
				
		return response;
		
	}
	
	public static int getMaxAutoincrement() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int id = 0;
		
		try {
			
			con = Conexion.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT MAX(id_documento) FROM documento; ");
			rs.next();
			id = rs.getInt(1);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(stmt);
			Conexion.close(rs);
		}
		
		return id;
	}
	
}
