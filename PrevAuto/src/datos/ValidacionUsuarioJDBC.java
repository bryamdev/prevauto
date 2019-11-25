package datos;

import java.sql.*;

import domain.Response;

public class ValidacionUsuarioJDBC {
	
	private static final String SQL_SELECT = "SELECT pa.usuario.id_usuario FROM pa.usuario "
			+ " WHERE correo = ? AND contraseña = ?";
	
	public static Response validarUsuario(String correo, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Response response = new Response();
		
		try {
			con = Conexion.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT);
			pstmt.setString(1, correo);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				response.setMensaje(rs.getString(1));
			}else {
				response.setMensaje("Datos de login incorrectos!");
				response.setError(true);
			}
			
		}catch(SQLException e) {
			response.setMensaje("Error al intentar logear: " + e.getMessage());
			response.setError(true);
			e.printStackTrace();
		}
		
		
		return response;
		
	}
	
	
	 

}
