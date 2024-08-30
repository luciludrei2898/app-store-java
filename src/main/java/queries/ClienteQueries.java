package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.Conexion;
import entidades.Cliente;



public class ClienteQueries {
	
	// CRUD 
	
		// METODO INSERTAR CLIENTE
		
		public static boolean insertCliente(Cliente newCliente) {
	
			boolean flag = false;
	
			try (Connection connection = Conexion.open()) {
	
				// QUERY
	
				String query = "INSERT INTO cliente VALUES (null, ?)";
	
				// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY
	
				try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	
					ps.setString(1, newCliente.getNombre()); // PONEMOS EL NOMBRE, EL 1 INDICA QUE ? ES
	
					// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HA INSERTADO
					int nRows = ps.executeUpdate();
	
					// EN EL CASO DE QUE EL NUMERO DE FILAS INSERTADO ES 1 , SERIA TRUE ENTONCES, LA
					// FLAG TB SERIA TRUE
	
					flag = nRows == 1;
	
					// ESTA PARTE ES PARA OBTENER LA CLAVE PRIMARIA GENERADA PARA EL NUEVO ACTOR
	
					ResultSet generatedKeys = ps.getGeneratedKeys();
	
					if (generatedKeys.next()) {
						int newClienteKey = generatedKeys.getInt(1);
	
						System.out.println(" Clave del cliente insertado: " + newClienteKey);
					}
				}
	
			} catch (SQLException e) {
				System.err.println("ERROR.");
				e.printStackTrace();
			}
	
			return flag;
	
		}
	
		
		// METODO BORRAR CLIENTE POR ID
		
		public static boolean deleteCliente(int id) {
	
			boolean flag = false;
	
			try (Connection connection = Conexion.open()) {
	
				// QUERY
	
				String query = "DELETE FROM cliente WHERE id = ?";
	
				// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY
	
				try (PreparedStatement ps = connection.prepareStatement(query)) {
	
					ps.setInt(1, id); // PONEMOS EL NOMBRE, EL 1 INDICA QUE ? ES
					flag = true;
					
					// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HAN BORRADO
					int nRows = ps.executeUpdate();
					if (nRows == 1) {
						System.out.println(" Cliente con ID " + id + " eliminado correctamente.");
					} else {
						System.out.println(" ERROR. Problemas en borrar el cliente con ID: " + id);
					}
				}
	
			} catch (SQLException e) {
				System.err.println("ERROR.");
				e.printStackTrace();
			}
	
			return flag;
			
	
		}
	
	
		// METODO MODIFICAR CLIENTE POR ID
		
		public static boolean updateCliente(int id, String nombre) {
	
			boolean flag = false;
	
			try (Connection connection = Conexion.open()) {
	
				// QUERY
	
				String query = "UPDATE cliente SET nombre = ? WHERE id = ? ";
	
				// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY
	
				try (PreparedStatement ps = connection.prepareStatement(query)) {
	
					ps.setString(1, nombre); // PONEMOS EL NOMBRE
					ps.setInt(2, id); // PONEMOS EL ID
	
					flag = true;
					
					// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HAN BORRADO
					int nRows = ps.executeUpdate();
					if (nRows == 1) {
						System.out.println(" Cliente con ID " + id + " modificado correctamente.");
					} else {
						System.out.println(" ERROR. Problemas para modificar el cliente con ID: " + id);
					}
				}
	
			} catch (SQLException e) {
				System.err.println("ERROR.");
				e.printStackTrace();
			}
	
			return flag;
			
	
		}
		

}
