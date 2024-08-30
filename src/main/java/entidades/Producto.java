package entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Producto {
	
	// VARIABLES
	
	private int id;
	private String nombre;
	private double precio;
	
	// CONSTRUCTOR SEMILLENO
	
	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	// CONSTRUCTOR LLENO
	
	public Producto(int id, String nombre, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	// METODOS
	// GETTER & SETTER

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return " ->  [ " + id + " ] " + nombre + " || " + precio + " euros. || ";
	}
	
	
	public static void imprimirCompra(ArrayList<Producto> carrito, ArrayList<Integer> carritoUnidades) {
		

				File archivoNuevo = new File("./TicketCompra.txt");

					try {

						// EL ARCHIVO SE CREA

						archivoNuevo.createNewFile();

						// CREAMOS EL OBJETO ESCRITURA

						FileWriter escritor = new FileWriter(archivoNuevo);

						// CREAMOS EL OBJETO ESCRITURA MODO PRO

						PrintWriter pw = new PrintWriter(escritor);

						// PROCEDEMOS A ESCRIBIR EL TICKET

						// TITULO

						pw.println(" === TICKET DE COMPRA === ");


						// HACEMOS UN FOR QUE RECORRA LOS PRODUCTOS E IMPRIMA SU NOMBRE Y EL PRECIO
						
						double precioTotal = 0;

						for (int i = 0; i < carrito.size(); i++) {

							pw.println(" -> " + carrito.get(i).getNombre() + " - " 
									+ carritoUnidades.get(i) + " unidades - "
									+ carrito.get(i).getPrecio() * carritoUnidades.get(i) + " euros.");
							
							// APROVECHAMOS EL BUCLE Y SUMAMOS EL PRECIO
							precioTotal += carrito.get(i).getPrecio() * carritoUnidades.get(i);
						}
						

						// TOTAL DEL PRECIO

						pw.println(" === PRECIO TOTAL: " + precioTotal + " ===");
						
						pw.close();
												

					} catch (IOException e) {
						System.err.println("ERROR. No se puede imprimir el ticket de compra.");
						e.printStackTrace();
					}
				
		
	}
	
	

}
