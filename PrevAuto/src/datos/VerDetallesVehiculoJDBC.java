package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.VerDetalles;;

public class VerDetallesVehiculoJDBC {
	
	private static final String SQL_SELECT = "SELECT id_vehiculo, nombre, modelo, marca, "
			+ "placa FROM pa.vehiculo WHERE id_vehiculo = ?";
		
		//Toma todos los vehiculos de la base de datos
		//Devuelve una lista con objetos de tipo vehiculo
		public static List<VerDetalles> selectVehiculos(int vehiculoId){
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			VerDetalles verDetalles = null;
			List<VerDetalles> listaDetalles = new ArrayList<>();
			
			try {
				con = Conexion.getConnection();
				stmt = con.prepareStatement(SQL_SELECT);
				stmt.setInt(1, vehiculoId);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					verDetalles = new VerDetalles(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5));
					
					listaDetalles.add(verDetalles);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				Conexion.close(rs);
				Conexion.close(stmt);
				Conexion.close(con);
			}
			
			return listaDetalles;
			
		}
}
