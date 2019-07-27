package datos;

import java.sql.*;
import java.util.*;
import domain.*;

public class VehiculosJDBC {
	
private static final String SQL_VEHICULO = "SELECT id_vehiculo, nombre, url_foto FROM "
		+ "pa.vehiculo WHERE usuario_id = ?";

private static final String SQL_DETALLES = "SELECT id_vehiculo, nombre, modelo, marca,"
		+ "placa, url_foto FROM pa.vehiculo WHERE id_vehiculo = ?";
	
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
			stmt = con.prepareStatement(SQL_VEHICULO);
			stmt.setInt(1, usuarioId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				vehiculo = new Vehiculo(rs.getInt(1), rs.getString(2), rs.getString(3));
				
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
	
	
	public static List<Vehiculo> selectDetallesVehiculo(int vehiculoId){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vehiculo vehiculo = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		try {
			con = Conexion.getConnection();
			stmt = con.prepareStatement(SQL_DETALLES);
			stmt.setInt(1, vehiculoId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				vehiculo = new Vehiculo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				
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
}
