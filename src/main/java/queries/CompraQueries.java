package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.Conexion;

public class CompraQueries {
	
	public static int insertCompra(String concepto, int idCliente) {
		
		int newCompraKey = -1;

		try (Connection connection = Conexion.open()) {

			// QUERY

			String query = "INSERT INTO compra VALUES (null, ?, ?)";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				ps.setString(1, concepto); // PONEMOS EL CONCEPTO
				ps.setInt(2, idCliente); // PONEMOS EL ID


				// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HA INSERTADO
				int nRows = ps.executeUpdate();

				// EN EL CASO DE QUE EL NUMERO DE FILAS INSERTADO ES 1 , SERIA TRUE ENTONCES, LA
				// FLAG TB SERIA TRUE

				// ESTA PARTE ES PARA OBTENER LA CLAVE PRIMARIA GENERADA PARA EL NUEVO ACTOR

				ResultSet generatedKeys = ps.getGeneratedKeys();

				if (generatedKeys.next()) {
					 newCompraKey = generatedKeys.getInt(1);

					//System.out.println(" CLAVE DE LA COMPRA INSERTADA: " + newCompraKey);
				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return newCompraKey;

	}

	
}
