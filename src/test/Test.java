package test;

import java.sql.*;
import java.util.List;

import datos.*;
import domain.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*
		Usuario usuario = new Usuario();
		usuario.setNombre("Daniela");
		usuario.setApellido("Gomez");
		usuario.setEmail("daniela@hotmail.com");
		usuario.setPassword("daniela1234");
		usuario.setCedula(76534221L);
		usuario.setTelefono(3463465478L);
		
		//Response res = UsuariosJDBC.insertUsuario(usuario);
		Response res = UsuariosJDBC.deleteUsuario(23);
		System.out.println(res);
		
		*/
		
		/*
		Connection con = null;
		
		try {
			con = Conexion.getConnection();
			System.out.println("Conexion correcta!");
			System.out.println(con.getCatalog());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		List<Vehiculo> vehiculos = VehiculosJDBC.selectVehiculos(25);
		
		for(Vehiculo v : vehiculos) {
			System.out.println(v);
		}
		
	
		
	
	}

}