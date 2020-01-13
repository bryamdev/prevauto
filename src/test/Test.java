package test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

import core.ProcesoFechas;
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
		
		/*
		List<Vehiculo> vehiculos = VehiculosJDBC.selectVehiculos(25);
		
		for(Vehiculo v : vehiculos) {
			System.out.println(v);
		}
		*/
		
		/*
		Configuracion conf = new Configuracion();
		conf.setValor(20);
		conf.setUsuarioId(7);
		Response res = ConfiguracionJDBC.updateConfiguracion(conf);
		System.out.println(res);
		
		
		
		
		Configuracion conf2 = ConfiguracionJDBC.selectConfiguracion(7);
		System.out.println(conf2);
		*/
		
		/*
		LocalDate fecha = LocalDate.now();
		String fechaTexto = fecha.toString();
		
		LocalTime hora = LocalTime.now();
		String horaTexto = hora.toString();
		
		Date date = new Date();
		
		SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-DD");
		String fechaFormateada = formateador.format(date);
		
		System.out.println(fechaTexto);
		System.out.println(horaTexto);
		System.out.println(date);
		System.out.println(fechaFormateada);
		*/
		
		/*
		String fechaInicioTexto = "2020-10-11";
		String fechaFinalTexto = "2020-11-11";
		
		Date fechaInicioDate = ProcesoFechas.convertToDate(fechaInicioTexto);
		Date fechaFinalDate = ProcesoFechas.convertToDate(fechaFinalTexto);
		
		
		int dias = ProcesoFechas.diferenciaDias(fechaInicioDate, fechaFinalDate);
		System.out.println(dias);
		*/
		
		
		/*
		int idUsuario = 25;
		
		
		System.out.println("Dias de configuracion: " + ConfiguracionJDBC.selectDiasValue(idUsuario));
		
		
		List<Documento> documentos = DocumentosJDBC.selectDocumentosCro(idUsuario);
		for(Documento d : documentos) {
			System.out.println(d);
			
			Date fecha1 = new Date();
			Date fecha2 = ProcesoFechas.convertToDate(d.getFechaVencimiento());
			
			int dias2 = ProcesoFechas.diferenciaDias(fecha1, fecha2);
			System.out.println(dias2);
			
			int diasConf = ConfiguracionJDBC.selectDiasValue(idUsuario);
			if(dias2 <= diasConf && dias2>0) {
				Response res = AlertasJDBC.activarAlerta(d.getIdDocumento());
				System.out.println(res.getMensaje());
			}else {
				Response res = AlertasJDBC.desactivarAlerta(d.getIdDocumento());
				System.out.println(res.getMensaje());
			}
		}	
		*/
		
		/*
		int idUsuario = 40;
		Response response = AlertasJDBC.modificarEstadoAlertas(idUsuario);
		System.out.println(response);
		*/
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = Conexion.getConnection();
			stmt = con.createStatement();
			boolean res = stmt.execute("SELECT 1");
			System.out.println(con.getCatalog() + " " + res);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			Conexion.close(stmt);
			Conexion.close(con);
		}
		
		
	
	}

}
