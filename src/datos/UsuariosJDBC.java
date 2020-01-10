package datos;

import java.sql.*;
import domain.*;
import datos.Conexion;



public class UsuariosJDBC {
	
	private static final String SQL_SELECT = "SELECT id_usuario, nombre, apellido, email, "
			+ " password, cedula, telefono, url_foto FROM usuario WHERE id_usuario = ?;";
	
	private static final String SQL_INSERT = "INSERT INTO usuario (nombre, apellido, email,"
			+ " password, cedula, telefono) VALUES ( ?, ?, ?, ?, ?, ?);"; 
	
	private static final String SQL_UPDATE = "UPDATE usuario  set nombre = ?, apellido = ?,"
			+ " email = ?, password = ?, cedula = ?, telefono = ? "
			+ " WHERE id_usuario = ?;"; 
	
	private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?; ";
	
	public static Usuario selectUsuario(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, idUsuario);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(1));
				usuario.setNombre(rs.getString(2));
				usuario.setApellido(rs.getString(3));
				usuario.setEmail(rs.getString(4));
				usuario.setPassword(rs.getString(5));
				usuario.setCedula(rs.getLong(6));
				usuario.setTelefono(rs.getLong(7));
				usuario.setUrlFoto(rs.getString(8));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(pstmt);
			Conexion.close(con);
		}
		
		return usuario;		
		
	}
	
	public static Response insertUsuario(Usuario usuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT);
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getApellido());
			pstmt.setString(3, usuario.getEmail());
			pstmt.setString(4, usuario.getPassword());
			pstmt.setLong(5, usuario.getCedula());
			pstmt.setLong(6, usuario.getTelefono());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				//'Triger' que guarda configuracion despues de insertar el usuario
				Response resTriger = ConfiguracionJDBC.insertAfterInsertUsuario(getMaxAutoincrement());
				if(!resTriger.isError()) {
					response.setMensaje("El usuario se guardó correctamente!");
				}else {
					response.setMensaje("El usuario se guardó pero NO la configuracion!");
				}
				
			}else {
				response.setMensaje("EL usuario NO se guardó!");
				response.setError(true);
			}
			
		}catch(SQLException e){
			response.setMensaje("Error al registrar el usuario: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
	}
	
	public static Response updateUsuario(Usuario usuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getApellido());
			pstmt.setString(3, usuario.getEmail());
			pstmt.setString(4, usuario.getPassword());
			pstmt.setLong(5, usuario.getCedula());
			pstmt.setLong(6, usuario.getTelefono());
			//pstmt.setString(7, usuario.getUrlFoto());
			pstmt.setInt(7, usuario.getIdUsuario());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("El usuario se actualizó correctamente!");
			}else {
				response.setMensaje("El usuario NO se actualizó!");
				response.setError(true);
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al actualizar el usuario: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;
		
	}
	
	public static Response deleteUsuario(int idUsuario) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Response response = new Response();
		
		try {
			
			Response resTrigger = ConfiguracionJDBC.DeleteBeforeDeleteUsuario(idUsuario);
			Response resTrigger2 = VehiculosJDBC.deleteBeforeDeleteUsuario(idUsuario);
			
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, idUsuario);
			
			if(!resTrigger.isError() && !resTrigger2.isError()) {
				
				int res = pstmt.executeUpdate();
				
				if(res != 0) {
					response.setMensaje("El usuario se eliminó correctamente!");
				}else {
					response.setMensaje("El usuario NO se eliminó!");
					response.setError(true);	
				}
			}else if(resTrigger.isError()) {
				response.setMensaje(resTrigger.getMensaje());
				response.setError(true);
				
			}else if(resTrigger2.isError()) {
				response.setMensaje(resTrigger2.getMensaje());
				response.setError(true);
			}
			
		}catch(Exception e) {
			response.setMensaje("Error al intentar elminar el usuario: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}finally {
			Conexion.close(con);
			Conexion.close(pstmt);
		}
		
		return response;		
	}
	
	public static int getMaxAutoincrement() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int id = 0;
		
		try {
			
			con = Conexion.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT MAX(id_usuario) FROM usuario; ");
			rs.next();
			id = rs.getInt(1);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(con);			
		}
		
		return id;
	}
	
	
}


