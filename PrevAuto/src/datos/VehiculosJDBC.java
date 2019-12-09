package datos;

import java.sql.*;
import java.util.*;
import domain.*;

public class VehiculosJDBC {
	
	private static final String SQL_LISTAR = "SELECT id_vehiculo, nombre, marca, url_foto FROM "
			+ " pa.vehiculo WHERE usuario_id = ?";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_vehiculo, nombre, modelo, marca, "
			+ " placa, usuario_id, url_foto FROM pa.vehiculo WHERE id_vehiculo = ?";
	
	private static final String SQL_INSERT = "INSERT INTO pa.vehiculo(nombre, modelo, marca, placa, "
			+ " usuario_id, url_foto) VALUES (?, ?, ?, ?, ?, ?);";
	
	private static final String SQL_UPDATE = "UPDATE pa.vehiculo set nombre = ?, modelo = ?, "
			+ " marca = ?, placa = ?, url_foto = ? WHERE id_vehiculo = ?;";
	
	private static final String SQL_DELETE = "DELETE FROM pa.vehiculo WHERE id_vehiculo = ?;";
	
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
				vehiculo = new Vehiculo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
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
			pstmt.setString(6, vehiculo.getUrlFoto());
			
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
			pstmt.setString(5, vehiculo.getUrlFoto());
			pstmt.setInt(6, vehiculo.getIdVehiculo());
			
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
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, idVehiculo);
			
			int res = pstmt.executeUpdate();
			
			if(res!=0) {
				response.setMensaje("El vehiculo se eliminó correctamente!");
			}else {
				response.setMensaje("El vehiculo NO se eliminó!");
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
}
