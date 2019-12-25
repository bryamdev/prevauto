package test;

import java.sql.*;

import datos.*;
import domain.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Daniela");
		usuario.setApellido("Gomez");
		usuario.setEmail("daniela@hotmail.com");
		usuario.setPassword("daniela1234");
		usuario.setCedula(76534221L);
		usuario.setTelefono(3463465478L);
		
		
		Response res = UsuariosJDBC.insertUsuario(usuario);
		System.out.println(res);
		
		/*
		int idUsuario = 23;
		Response res = UsuariosJDBC.deleteUsuario(idUsuario);
		
		System.out.println(res);
		*/
		
		
		/*
		try {
			
			Connection con = Conexion.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id_config) FROM pa.configuracion;");
			rs.next();
			int idUsuario = rs.getInt(1);
			System.out.println("id: " + idUsuario);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		*/
		
				
		

	}

}
