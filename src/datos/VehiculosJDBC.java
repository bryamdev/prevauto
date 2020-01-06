package datos;

import java.sql.*;
import java.util.*;
import domain.*;

public class VehiculosJDBC {
	
	private static final String SQL_LISTAR = "SELECT id_vehiculo, nombre, marca, url_foto FROM "
			+ " vehiculo WHERE usuario_id = ?";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_vehiculo, nombre, modelo, marca, "
			+ " placa, usuario_id, url_foto FROM vehiculo WHERE id_vehiculo = ?";
	
	private static final String SQL_INSERT = "INSERT INTO vehiculo(nombre, modelo, marca, "
			+ " placa, usuario_id) VALUES (?, ?, ?, ?, ?);";
	
	private static final String SQL_UPDATE = "UPDATE vehiculo set nombre = ?, modelo = ?, "
			+ " marca = ?, placa = ? WHERE id_vehiculo = ?;";
	
	private static final String SQL_DELETE = "DELETE FROM vehiculo WHERE id_vehiculo = ?;";
	
	private static final String SQL_SELECT_BY_USUARIO_ID = "SELECT id_vehiculo FROM vehiculo "
			+ " WHERE usuario_id = ?; ";
	
	//Toma todos los vehiculos de la base de datos
	//Devuelve una lista con objetos de tipo vehiculo
	public static List<Vehiculo> selectVehiculos(int usuarioId){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vehiculo vehiculo = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_LISTAR);
			stmt.setInt(1, usuarioId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setIdVehiculo(rs.getInt(1));
				vehiculo.setNombre(rs.getString(2));
				vehiculo.setMarca(rs.getString(3));
				vehiculo.setUrlFoto(rs.getString(4));
				
				vehiculos.add(vehiculo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		return vehiculos;
		
	}
	
	
	public static Vehiculo selectDetallesById(int vehiculoId){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vehiculo vehiculo = null;
	
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, vehiculoId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setIdVehiculo(rs.getInt(1));
				vehiculo.setNombre(rs.getString(2));
				vehiculo.setModelo(rs.getString(3));
				vehiculo.setMarca(rs.getString(4));
				vehiculo.setPlaca(rs.getString(5));
				vehiculo.setUsuarioId(rs.getInt(6));
				vehiculo.setUrlFoto(rs.getString(7));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		return vehiculo;
		
	}
	
	public static Response insertVehiculo(Vehiculo vehiculo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vehiculo.getNombre());
			pstmt.setString(2, vehiculo.getModelo());
			pstmt.setString(3, vehiculo.getMarca());
			pstmt.setString(4, vehiculo.getPlaca());
			pstmt.setInt(5, vehiculo.getUsuarioId());
			//pstmt.setString(6, vehiculo.getUrlFoto());
			
			int res = pstmt.executeUpdate();
			
			if(res!=0) {
				response.setMensaje("El vehiculo se registró correctamente! ");
			}else {
				response.setMensaje("El vehiculo NO se registró!!");
				response.setError(true);
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar registrar el vehiculo: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return response;
	}
	
	public static Response updateVehiculo(Vehiculo vehiculo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, vehiculo.getNombre());
			pstmt.setString(2, vehiculo.getModelo());
			pstmt.setString(3, vehiculo.getMarca());
			pstmt.setString(4, vehiculo.getPlaca());
			//pstmt.setString(5, vehiculo.getUrlFoto());
			pstmt.setInt(5, vehiculo.getIdVehiculo());
			
			int res = pstmt.executeUpdate();
			
			if(res!=0) {
				response.setMensaje("El vehiculo se actualizó correctamente!");
			}else {
				response.setMensaje("El vehiculo NO se actualizó!");
				response.setError(true);
			}
			
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar actualizar el vehiculo: " +e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return response;	
	}
	
	public static Response deleteVehiculo(int idVehiculo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			
			Response resTrigger = DocumentosJDBC.deleteDocumentoBeforeDeleteVehiculo(idVehiculo);
			
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, idVehiculo);
			
			
			if(!resTrigger.isError()) {
				int res = pstmt.executeUpdate();
				
				if(res!=0) {
					response.setMensaje("El vehiculo se eliminó correctamente!");
				}else {
					response.setMensaje("El vehiculo NO se eliminó!");
					response.setError(true);
				}
				
			}else {
				response.setMensaje(resTrigger.getMensaje());
				response.setError(true);
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar eliminar el vehiculo: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return response;
	}
	
	public static Response deleteBeforeDeleteUsuario(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_USUARIO_ID);
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				deleteVehiculo(rs.getInt(1));
			}
			
			response.setMensaje("Los vehiculos se eliminaron correctamente!");
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar eliminar los vehiculos: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		
		return response;
		
	}
	
	
}
