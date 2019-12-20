package datos;

import java.sql.*;
import domain.*;
import datos.Conexion;



public class UsuariosJDBC {
	
	private static final String SQL_SELECT = "SELECT pa.usuario.id_usuario, pa.usuario.nombre, pa.usuario.apellido, "
			+ " pa.usuario.correo, pa.usuario.contraseña, pa.usuario.cedula, pa.usuario.telefono, "
			+ " pa.usuario.url_foto FROM pa.usuario WHERE id_usuario = ? ";
	
	private static final String SQL_INSERT = "INSERT INTO pa.usuario (nombre, apellido, correo,"
			+ " contraseña, cedula, telefono) VALUES ( ?, ?, ?, ?, ?, ?);"; 
	
	private static final String SQL_UPDATE = "UPDATE pa.usuario  set nombre = ?, apellido = ?,"
			+ " correo = ?, contraseña = ?, cedula = ?, telefono = ?, "
			+ "url_foto = ? WHERE id_usuario = ?;"; 
	
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
				usuario.setCorreo(rs.getString(4));
				usuario.setContraseña(rs.getString(5));
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
			pstmt.setString(3, usuario.getCorreo());
			pstmt.setString(4, usuario.getContraseña());
			pstmt.setLong(5, usuario.getCedula());
			pstmt.setLong(6, usuario.getTelefono());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("El usuario se guardó correctamente!");
			}else {
				response.setMensaje("EL usuario NO se guardó!");
				response.setError(true);
			}
			
		}catch(SQLException e){
			response.setMensaje("Error al registrar el usuario: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
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
			pstmt.setString(3, usuario.getCorreo());
			pstmt.setString(4, usuario.getContraseña());
			pstmt.setLong(5, usuario.getCedula());
			pstmt.setLong(6, usuario.getTelefono());
			pstmt.setString(7, usuario.getUrlFoto());
			pstmt.setInt(8, usuario.getIdUsuario());
			
			int res = pstmt.executeUpdate();
			
			if(res != 0) {
				response.setMensaje("El usuario se actualizó correctamente!");
			}else {
				response.setMensaje("El usuario NO se actualizó!");
				response.setError(true);;
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al actualizar el usuario: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}
		
		return response;
		
	}

}
