package datos;

import java.sql.*;
import java.util.*;

import domain.Documento;

public class DocumentosJDBC {
	
private static final String SQL_SELECT = "SELECT id_documento, fecha_expedicion, fecha_vencimiento, "
		+ "tipo_documento FROM pa.documento WHERE vehiculo_id = ?";

private static final String SQL_SELECT_CRO = "SELECT documento.fecha_vencimiento, vehiculo.nombre,"
		+ "tipo_documento.nombre FROM pa.documento, pa.vehiculo, pa.tipo_documento, pa.usuario" + 
		"WHERE pa.usuario.id_usuario = pa.vehiculo.usuario_id" + 
		"AND pa.vehiculo.id_vehiculo = pa.documento.vehiculo_id" + 
		"AND pa.tipo_documento.id_tipo_documento = pa.documento.tipo_documento" + 
		"AND pa.usuario.id_usuario = ? order by pa.vehiculo.nombre;";


	//Toma todos los documentos de un vehiculo de la base de datos
	//Devuelve una lista con objetos de tipo Documento
	public static List<Documento> selectDocumentos(int vehiculoId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Documento documento = null;
		List<Documento> documentos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, vehiculoId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				documento = new Documento(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4));
				
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
				documento = new Documento(rs.getDate(1), rs.getString(2), rs.getString(3));
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
}
