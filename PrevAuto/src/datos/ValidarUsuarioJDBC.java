package datos;

import java.sql.*;

public class ValidarUsuarioJDBC {
	
	private final String SQL_SELECT = "SELECT correo, contraseña FROM pa.usuario WHERE correo = ? AND contraseña = ?";
	
	 

}
