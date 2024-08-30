package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	
	// ES LA CLASE QUE ESTABLECE CONEXIONES CON LA BBD
	// DEBE CREARSE EN ESTATICO

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tienda";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "AlumnoIFP";

	// METODO

	// ABRE UNA CONEXION Y RETORNARLA A TRAVES DE LA CLASE CONNECTION

	public static Connection open() throws SQLException {
		Properties props = new Properties(); // OBJETO QUE RECOGE LAS PROPIEDADES DE USUARIO Y PASSWORD

		props.setProperty("user", USUARIO); // ESTABLECE QUE USER ES LA CONSTANTE QUE HEMOS CREADO ARRIBA DE USUARIO
		props.setProperty("password", PASSWORD); // ESTABLECE QUE PASSWORD ES LA CONSTANTE QUE HEMOS CREADO ARRIBA DE
													// USUARIO

		// props.setProperty("SSL", "TRUE"); // PARA REALIZAR UNA CONEXION SEGURA
		return DriverManager.getConnection(URL, props);

}
	
}
