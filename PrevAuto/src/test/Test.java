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
		*/
		
		
		/*
		Response res = UsuariosJDBC.insertUsuario(usuario);
		System.out.println(res);
		*/
		
		/*
		int idUsuario = 23;
		Response res = UsuariosJDBC.deleteUsuario(idUsuario);
		
		System.out.println(res);
		*/
		
		/*
		Response res = VehiculosJDBC.deleteVehiculo(17);
		System.out.println(res);
		*/
		
		/*Response res = AlertasJDBC.insertAfterInsertDocumento(8);
		System.out.println(res);
		*/
		
		
		
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
		
		
		/*
		List<Alerta> res = AlertasJDBC.selectAlertas(21);
		if(res.size()!=0) {
			for(Alerta a : res) {
				System.out.println(a);
			}
		}else {
			System.out.println("No hay aleras!");
		}
		
		*/
		
	
	}

}
