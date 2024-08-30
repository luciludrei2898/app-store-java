package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexiones.Conexion;
import entidades.Producto;

public class ProductoQueries {
	
	// CRUD
	
	// METODO INSERTAR PRODUCTO
	
	public static boolean insertProducto(Producto newProducto) {

		boolean flag = false;

		try (Connection connection = Conexion.open()) {

			// QUERY

			String query = "INSERT INTO producto VALUES (null, ?, ?)";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				ps.setString(1, newProducto.getNombre()); // PONEMOS EL NOMBRE, EL 1 INDICA QUE ? ES
				ps.setDouble(2, newProducto.getPrecio()); // PONEMOS EL NOMBRE, EL 1 INDICA QUE ? ES


				// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HA INSERTADO
				int nRows = ps.executeUpdate();

				// EN EL CASO DE QUE EL NUMERO DE FILAS INSERTADO ES 1 , SERIA TRUE ENTONCES, LA
				// FLAG TB SERIA TRUE

				flag = nRows == 1;

				// ESTA PARTE ES PARA OBTENER LA CLAVE PRIMARIA GENERADA PARA EL NUEVO ACTOR

				ResultSet generatedKeys = ps.getGeneratedKeys();

				if (generatedKeys.next()) {
					int newProductoKey = generatedKeys.getInt(1);

					System.out.println("\n Clave del producto insertado: " + newProductoKey);
				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return flag;

	}

	
	// METODO BORRAR PRODUCTO POR ID
	
	public static boolean deleteProducto(int id) {

		boolean flag = false;

		try (Connection connection = Conexion.open()) {

			// QUERY

			String query = "DELETE FROM producto WHERE id = ?";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query)) {

				ps.setInt(1, id); // PONEMOS EL ID
				flag = true;
				
				// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HAN BORRADO
				int nRows = ps.executeUpdate();
				if (nRows == 1) {
					System.out.println(" Producto con ID " + id + " eliminado correctamente.");
				} else {
					System.out.println(" ERROR. Problemas en borrar el producto con ID: " + id);
				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return flag;
		

	}


	// METODO MODIFICAR PRODUCTO POR ID
	
	public static boolean updateProducto(int id, String nombre, double precio) {

		boolean flag = false;

		try (Connection connection = Conexion.open()) {

			// QUERY

			String query = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ? ";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query)) {

				ps.setString(1, nombre); // PONEMOS EL NOMBRE
				ps.setDouble(2, precio); // PONEMOS EL PRECIO
				ps.setInt(3, id); // PONEMOS EL ID

				flag = true;
				
				// ESTE METODO NOS DEVUELVE EL NUMERO DE FILAS QUE SE HAN MODIFICADO
				
				int nRows = ps.executeUpdate();
				
				if (nRows == 1) {
					System.out.println(" Producto con ID: " + id + ",  modificado correctamente.");
				} else {
					System.out.println(" ERROR. Problemas para modificar el producto con ID: " + id);
				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return flag;
		

	}
	
	// SACAR TODOS LOS PRODUCTOS
	
	public static ArrayList<Producto> getAllProducto() {

		ArrayList<Producto> productos = new ArrayList<>();

		try (Connection connection = Conexion.open()) {
			
			String query = "SELECT * FROM producto";


			try (PreparedStatement ps = connection.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						int id = rs.getInt("id"); 
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						productos.add(newProducto);
						System.out.println(newProducto.toString());
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return productos;

	}

	// SACAR TODOS LOS PRODUCTOS POR ORDEN ASCENDENTE 
	
	public static ArrayList<Producto> getAllProductoASC() {

		ArrayList<Producto> productosASC = new ArrayList<>();

		try (Connection connection = Conexion.open()) {
			
			String query = "SELECT * FROM producto ORDER BY precio ASC";


			try (PreparedStatement ps = connection.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						int id = rs.getInt("id"); 
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						productosASC.add(newProducto);
						System.out.println(newProducto.toString());
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return productosASC;

	}


	// SACAR TODOS LOS PRODUCTOS POR ORDEN DESCENDENTE 
	
	public static ArrayList<Producto> getAllProductoDESC() {

		ArrayList<Producto> productosASC = new ArrayList<>();

		try (Connection connection = Conexion.open()) {
			
			String query = "SELECT * FROM producto ORDER BY precio DESC";


			try (PreparedStatement ps = connection.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						int id = rs.getInt("id"); 
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						productosASC.add(newProducto);
						System.out.println(newProducto.toString());
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return productosASC;

	}

	// SACAR TODOS LOS PRODUCTOS ALFABETICAMENTE 
	
	public static ArrayList<Producto> getAllProductoALF() {

		ArrayList<Producto> productosASC = new ArrayList<>();

		try (Connection connection = Conexion.open()) {
			
			String query = "SELECT * FROM producto ORDER BY nombre ASC";


			try (PreparedStatement ps = connection.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						int id = rs.getInt("id"); 
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						productosASC.add(newProducto);
						System.out.println(newProducto.toString());
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return productosASC;

	}
	
	
	// SACAR TODOS LOS PRODUCTOS CON FILTRO 
	
	public static ArrayList<Producto> getProductoBySearch(String pattern) {

		ArrayList<Producto> productos = new ArrayList<>();

		try (Connection connection = Conexion.open()) {

			String query = "SELECT * FROM producto WHERE nombre LIKE ?";

			// NOS PERMITE SUSTITUIR LAS PARTES DE LA QUERY

			try (PreparedStatement ps = connection.prepareStatement(query)) {
				ps.setString(1, "%" + pattern + "%");

				// ESTE ES EL QUE REALMENTE EJECUTA LA QUERY
				try (ResultSet rs = ps.executeQuery()) {

					// .NEXT DEVUELVE UN TRUE O FALSE, FALSE CUANDO YA NO
					// HAY NADA MAS QUE LEER

					while (rs.next()) {
						
						int id = rs.getInt("id"); 
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						productos.add(newProducto);
						System.out.println(newProducto.toString());
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return productos;

	}


	// METER EL PRODUCTO EN CARRITO Y SACAR EL PRODUCTO SELECCIONADO

	public static ArrayList<Producto> getProductoCarritoCompra(int id, ArrayList<Producto> carrito) {


		try (Connection connection = Conexion.open()) {
			
			String query = "SELECT * FROM producto WHERE id = ?";


			try (PreparedStatement ps = connection.prepareStatement(query)) {
				
				ps.setInt(1, id); // PONEMOS EL NOMBRE, EL 1 INDICA QUE ? ES


				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");

						Producto newProducto = new Producto(id, nombre, precio);
						carrito.add(newProducto);
						System.out.println("\n -> Producto introducido: " + nombre + ", " + precio + " euros.");
					}

				}
			}

		} catch (SQLException e) {
			System.err.println("ERROR.");
			e.printStackTrace();
		}

		return carrito;

	}


}
