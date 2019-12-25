package datos;

import java.sql.*;
import java.util.*;

import domain.Alerta;

public class AlertasJDBC {
	
	private static final String SQL_SELECT = "SELECT pa.tipo_documento.nombre, pa.documento.numero, pa.vehiculo.nombre, pa.documento.fecha_vencimiento " + 
			"	FROM pa.alerta, pa.documento, pa.tipo_documento, pa.vehiculo, pa.usuario " + 
			"    WHERE pa.alerta.documento_id = pa.documento.id_documento " + 
			"    AND pa.documento.vehiculo_id = pa.vehiculo.id_vehiculo " + 
			"	AND pa.documento.tipo_documento = pa.tipo_documento.id_tipo_documento " + 
			"    AND pa.vehiculo.usuario_id = pa.usuario.id_usuario " + 
			"    AND pa.usuario.id_usuario = ?" + 
			"    AND pa.alerta.activo = 1";
	
	private static final String SQL_INSERT_AFTER_INSERT_DOCUMENTO = "";
		
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
}
