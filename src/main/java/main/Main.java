package main;


import java.util.ArrayList;
import java.util.Scanner;
import entidades.Cliente;
import entidades.Producto;
import queries.ClienteQueries;
import queries.CompraProductoQueries;
import queries.CompraQueries;
import queries.ProductoQueries;

public class Main {

	public static void main(String[] args) {

		// OBJETO SCANNER

		Scanner sc = new Scanner(System.in);

		// VARIABLES

		int numIdentificacion;
		boolean flagIdentificacion = true;

		while (flagIdentificacion) {

			System.out.print(" === IDENTIFICACION === \n" + " \n Introduce tu tipo de usuario: \n"
					+ "\n [ 1 ] ADMINISTRADOR. \n [ 2 ] CLIENTE.  \n [ 3 ] SALIR. \n" + "\n >>> ");
			numIdentificacion = sc.nextInt();

			if (numIdentificacion == 1) {
				menuAdministrador();
				flagIdentificacion = false;
			} else if (numIdentificacion == 2) {
				menuCliente();
				flagIdentificacion = false;

			} else if (numIdentificacion == 3) {
				System.out.println(" .");
				System.out.println(" ..");
				System.out.println(" ...");
				System.out.println(" \n CIERRE DE SESION.");
				flagIdentificacion = false;
				sc.close();
			} else {
				System.out.println(" Error. Opcion desconocida. Intentelo de nuevo.");
			}

		}

	}

	public static void menuAdministrador() {

		// OBJETO SCANNER

		Scanner sc = new Scanner(System.in);

		// VARIABLES

		boolean flagMenuAdministrador = true;
		int opcionMenu;

		// VARIABLES ELIMINAR

		int idProductoEliminar;
		int idClienteEliminar;

		// VARIABLES INSERTAR

		String nombreCliente;
		String nombreProducto;
		double precioProducto;

		// VARIABLES MODIFICAR

		int idProductoModificar;
		int idClienteModificar;
		String nombreClienteModificar;
		String nombreProductoModificar;
		double precioProductoModificar;

		while (flagMenuAdministrador) {

			System.out.print(" \n === MENU DE ADMINISTRADOR === \n " + " \n Introduzca la opcion deseada: \n \n"
					+ " [ 1 ] DAR DE ALTA A UN NUEVO CLIENTE. \n" + " [ 2 ] DAR DE ALTA A UN NUEVO PRODUCTO. \n"
					+ " [ 3 ] MODIFICAR DATOS CLIENTE. \n" + " [ 4 ] MODIFICAR DATOS PRODUCTO. \n"
					+ " [ 5 ] ELIMINAR UN CLIENTE POR SU ID. \n" + " [ 6 ] ELIMINAR UN PRODUCTO POR SU ID. \n"
					+ " [ 7 ] SALIR. \n" + " \n >>> ");

			opcionMenu = sc.nextInt();
			sc.nextLine();

			switch (opcionMenu) {
			case 1:
				System.out.print("\n Introduzca el nombre del nuevo cliente: ");
				nombreCliente = sc.nextLine();
				// CONSTRUIMOS EL CLIENTE
				Cliente nuevoCliente = new Cliente(nombreCliente);
				ClienteQueries.insertCliente(nuevoCliente);
				break;
			case 2:
				System.out.print("\n Introduzca el nombre del nuevo producto: ");
				nombreProducto = sc.nextLine();
				System.out.print("\n Introduzca el precio del nuevo producto: ");
				precioProducto = sc.nextDouble();
				sc.nextLine();
				// CONSTRUIMOS EL PRODUCTO
				Producto nuevoProducto = new Producto(nombreProducto, precioProducto);
				ProductoQueries.insertProducto(nuevoProducto);
				break;
			case 3:
				System.out.print("\n Introduzca el ID del cliente a modificar: ");
				idClienteModificar = sc.nextInt();
				sc.nextLine();
				System.out.print("\n Introduzca el nuevo NOMBRE del cliente a modificar: ");
				nombreClienteModificar = sc.nextLine();

				ClienteQueries.updateCliente(idClienteModificar, nombreClienteModificar);
				break;
			case 4:
				System.out.print("\n Introduzca el ID del producto a modificar: ");
				idProductoModificar = sc.nextInt();
				sc.nextLine();
				System.out.print("\n Introduzca el nuevo NOMBRE del producto a modificar: ");
				nombreProductoModificar = sc.nextLine();
				System.out.print("\n Introduzca el nuevo PRECIO del producto a modificar: ");
				precioProductoModificar = sc.nextDouble();
				sc.nextLine();
				ProductoQueries.updateProducto(idProductoModificar, nombreProductoModificar, precioProductoModificar);
				break;
			case 5:
				System.out.print(" \n Introduzca el ID del cliente que desea eliminar: ");
				idClienteEliminar = sc.nextInt();
				ClienteQueries.deleteCliente(idClienteEliminar);
				break;
			case 6:
				System.out.print(" \n Introduzca el ID del producto que desea eliminar: ");
				idProductoEliminar = sc.nextInt();
				ProductoQueries.deleteProducto(idProductoEliminar);

				break;
			case 7:
				System.out.println(" .");
				System.out.println(" ..");
				System.out.println(" ...");
				System.out.println(" \n CIERRE DE SESION.");
				flagMenuAdministrador = false;
				break;
			default:
				System.out.println("Error: opcion desconocida. Intentelo de nuevo.");

			}
		}

		sc.close();

	}

	public static void menuCliente() {

		// OBJETO SCANNER

		Scanner sc = new Scanner(System.in);

		// VARIABLES

		int idCliente;
		boolean flagMenuCliente = true;
		int opcionMenu;
		int opcionMenuCatalogo;
		String filtro;
		boolean flagCompra = true;
		int productoElegidoID;
		ArrayList<Producto> carrito = new ArrayList<>();
		ArrayList<Integer> unidadesCarrito = new ArrayList<>();
		int seguirCompra;
		String conceptoCompra;
		int cantidadProductos = 0;
		int compraCliente;
		
		System.out.print( "\n Introduzca su codigo de cuenta (ID): ");
		idCliente = sc.nextInt();


		while (flagMenuCliente) {

			System.out.print(" \n === MENU DE CLIENTE  === \n " + " \n Introduzca la opcion deseada: \n \n"
					+ " [ 1 ] VER EL CATALOGO. \n" + " [ 2 ] SELECCIONAR LOS PRODUCTOS PARA INTRODUCIR EN LA COMPRA. \n"
					+ " [ 3 ] EFECTUAR LA COMPRA E IMPRIMIR EL TICKET EN UN ARCHIVO DE TEXTO. \n" + " [ 4 ] SALIR. \n"
					+ " \n >>> ");
			opcionMenu = sc.nextInt();

			switch (opcionMenu) {
			case 1:
				System.out.print(" \n Introduzca la opcion deseada: \n \n"
						+ " [ 1 ] Organizado ascendentemente por precio. \n"
						+ " [ 2 ] Organizado descendentemente por precio. \n"
						+ " [ 3 ] Organizado alfabeticamente por nombre. \n"
						+ " [ 4 ] Filtro de productos por coincidencia de caracteres. \n"
						+ " \n >>> ");
				opcionMenuCatalogo = sc.nextInt();

				switch (opcionMenuCatalogo) {
				case 1:
					System.out.println();
					ProductoQueries.getAllProductoASC();
					break;
				case 2:
					System.out.println();
					ProductoQueries.getAllProductoDESC();
					break;
				case 3:
					System.out.println();
					ProductoQueries.getAllProductoALF();
					break;
				case 4:
					System.out.print(" \n Introduce la cadena para filtrar: ");
					filtro = sc.next();
					ProductoQueries.getProductoBySearch(filtro);
					break;
				default:
					System.out.println(" Error: opcion desconocida. Intentelo de nuevo.");
				}

				break;
			case 2:
				
				// MOSTRAMOS LOS PRODUCTOS
				System.out.println();
				ProductoQueries.getAllProducto();
				// LO PONEMOS EN TRUE
				flagCompra = true;
				
				while(flagCompra) {
					
					System.out.print( " \n Introduce los productos deseados: ");
					productoElegidoID = sc.nextInt();
					System.out.print( " \n Introduce las unidades deseadas: ");
					cantidadProductos = sc.nextInt();
					unidadesCarrito.add(cantidadProductos);
					
					ProductoQueries.getProductoCarritoCompra(productoElegidoID, carrito);
					System.out.print(" \n Desea seguir con la compra? [ 1 ] SI. [ 2 ] NO. : ");
					seguirCompra = sc.nextInt();
					sc.nextLine();

					
					if (seguirCompra == 2) {
						flagCompra = false;
					}
					
				}
				
				System.out.println("\n ." + "\n .." + "\n ... " + " VOLVIENDO AL MENU.");
				
				break;
			case 3:
				
				// IMPRIMIMOS EL TICKET
				
				Producto.imprimirCompra(carrito, unidadesCarrito);
				
				// PREGUNTAMOS EL CONCEPTO
				sc.nextLine();
				System.out.print( "\n Introduce el concepto de compra: ");
				conceptoCompra = sc.nextLine();
				
				// CREAMOS LA COMPRA CON LA COMPRAQUERIES
				
				compraCliente = CompraQueries.insertCompra(conceptoCompra, idCliente);
				
				// AHORA INSERTAMOS LA COMPRA-PRODUCTO
				
				for (int i = 0; i < carrito.size(); i++) {
					
					CompraProductoQueries.insertCompraProducto(compraCliente,carrito.get(i).getId(),unidadesCarrito.get(i));
					
				}
				
				
				break;
			case 4:
				System.out.println(" .");
				System.out.println(" ..");
				System.out.println(" ...");
				System.out.println(" \n CIERRE DE SESION.");
				flagMenuCliente = false;
				break;

			default:
				System.out.println("Error: opcion desconocida. Intentelo de nuevo.");

			}
		}
		
		sc.close();
	}

}
