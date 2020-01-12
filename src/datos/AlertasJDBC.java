package datos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import core.ProcesoFechas;
import domain.Alerta;
import domain.Documento;
import domain.Response;

public class AlertasJDBC {
	
	private static final String SQL_SELECT = "SELECT tipo_documento.nombre, documento.numero, "
			+ " vehiculo.nombre, documento.fecha_vencimiento "
			+ " FROM alerta, documento, tipo_documento, vehiculo, usuario "
			+ " WHERE alerta.documento_id = documento.id_documento "
			+ " AND documento.vehiculo_id = vehiculo.id_vehiculo "
			+ " AND documento.tipo_documento = tipo_documento.id_tipo_documento "
			+ " AND vehiculo.usuario_id = usuario.id_usuario "
			+ " AND usuario.id_usuario = ? AND alerta.activo = 1; ";
	
	private static final String SQL_ESTADO_ALERTA = "UPDATE alerta SET activo = ? WHERE documento_id = ?; ";
	
	private static final String SQL_INSERT_AFTER_INSERT_DOCUMENTO = "INSERT INTO alerta(activo, documento_id) "
			+ " VALUES (0, ?);";
	
	private static final String SQL_DELETE_BEFORE_DELETE_DOCUMENTO = "DELETE FROM alerta "
			+ " WHERE documento_id = ?";
	
		
	//Toma todas las alertas de la base de datos
	//Devuelve una lista con objetos de tipo alertas
	public static List<Alerta> selectAlertas(int idUsuario){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Alerta> alertas = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, idUsuario);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Alerta alerta = new Alerta();
				alerta.setTipoDocumentoNombre(rs.getString(1));
				alerta.setNumeroDocumento(rs.getLong(2));
				alerta.setNombreVehiculo(rs.getString(3));
				alerta.setFechaVencimiento(rs.getString(4));
				
				Date fechaActual = new Date();
				Date fechaDoc = ProcesoFechas.convertToDate(rs.getString(4));
				int diasRestantes = ProcesoFechas.diferenciaDias(fechaActual, fechaDoc);
				
				alerta.setDiasRestantes(diasRestantes+1);
				
				alertas.add(alerta);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return alertas;	
	}
	
	public static Response activarAlerta(int idDocumento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_ESTADO_ALERTA);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, idDocumento);
			
			int res = pstmt.executeUpdate();
			if(res != 0) {
				response.setMensaje("La alerta se activó correctamente!");
			}else {
				response.setMensaje("La alerta NO se activó!");
				response.setError(true);
			}
			
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar activar la alerta: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
		
	}
	
	public static Response desactivarAlerta(int idDocumento) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_ESTADO_ALERTA);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, idDocumento);
			
			int res = pstmt.executeUpdate();
			if(res != 0) {
				response.setMensaje("La alerta se desactivó correctamente!");
			}else {
				response.setMensaje("La alerta NO se desactivó!");
				response.setError(true);
			}
			
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar desactivar la alerta: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
		
	}
	
	public static Response modificarEstadoAlertas(int idUsuario) {
		
		Response response = new Response();
		
		int diasConf = ConfiguracionJDBC.selectDiasValue(idUsuario);
		List<Documento> documentos = DocumentosJDBC.selectDocumentosCro(idUsuario);
		
		System.out.println(diasConf);
		
		if(documentos.size() != 0) {
			
			for(Documento documento : documentos) {
				
				Date fechaActual = new Date();
				Date fechaDocumento = ProcesoFechas.convertToDate(documento.getFechaVencimiento());
				
				int diasDiferencia = ProcesoFechas.diferenciaDias(fechaActual, fechaDocumento);
				System.out.println(diasDiferencia);
				
				if(diasDiferencia <= diasConf && diasDiferencia >= 0) {
					Response res = AlertasJDBC.activarAlerta(documento.getIdDocumento());
					System.out.println(res.getMensaje());
				}else {
					Response res = AlertasJDBC.desactivarAlerta(documento.getIdDocumento());
					System.out.println(res.getMensaje());
					//System.out.println(documento.getIdDocumento());
				}
				System.out.println(documento);
			}
			response.setMensaje("Se modificaron " + documentos.size() + " alertas");
		}else {
			response.setMensaje("No habian alertas para modificar");
			response.setError(true);
		}
		
		return response;		
		
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
