package datos;

import java.sql.*;
import java.util.*;
import domain.*;

public class VehiculosJDBC {
	
private static final String SQL_SELECT = "SELECT id_vehiculo, nombre, url_foto FROM "
		+ "pa.vehiculo WHERE usuario_id = ?";
	
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
			stmt = con.prepareStatement(SQL_SELECT);
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
}
