package entidades;

public class CompraProducto {
	
	private Compra id_compra;
	private Producto id_producto;
	private int unidades;
	
	// COSNTRUCTOR
	
	public CompraProducto(Compra id_compra, Producto id_producto, int unidades) {
		this.id_compra = id_compra;
		this.id_producto = id_producto;
		this.unidades = unidades;
	}
	
	// METODOS
	// GETTER & SETTER

	public Compra getId_compra() {
		return id_compra;
	}

	public void setId_compra(Compra id_compra) {
		this.id_compra = id_compra;
	}

	public Producto getId_producto() {
		return id_producto;
	}

	public void setId_producto(Producto id_producto) {
		this.id_producto = id_producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	

}
