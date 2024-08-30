package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.Conexion;

public class CompraProductoQueries {
	
public static boolean insertCompraProducto(int idCompra, int idProducto, int unidades) {
		
		boolean flag = false;

		try (Connection connection = Conexion.open()) {

			// QUERY

			String query = "INSERT INTO compra_producto VALUES (?, ?, ?)";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				ps.setInt(1, idCompra); // PONEMOS EL ID COMPRA
				ps.setInt(2, idProducto); // PONEMOS EL ID
				ps.setInt(3, unidades); // PONEMOS EL ID



				// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HA INSERTADO
				int nRows = ps.executeUpdate();

				// EN EL CASO DE QUE EL NUMERO DE FILAS INSERTADO ES 1 , SERIA TRUE ENTONCES, LA
				// FLAG TB SERIA TRUE

				flag = nRows == 1;

				// ESTA PARTE ES PARA OBTENER LA CLAVE PRIMARIA GENERADA PARA EL NUEVO ACTOR

				ResultSet generatedKeys = ps.getGeneratedKeys();

				if (generatedKeys.next()) {
					int newActorKey = generatedKeys.getInt(1);

					System.out.println("Clave de la compra_producto insertada: " + newActorKey);
				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return flag;

	}

}
