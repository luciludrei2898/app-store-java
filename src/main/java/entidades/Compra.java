package entidades;

public class Compra {
	
	// VARIABLES
	
	private int id;
	private String concepto;
	private Cliente id_cliente;
	
	// CONSTRUCTOR
	
	public Compra(int id, String concepto, Cliente id_cliente) {
		this.id = id;
		this.concepto = concepto;
		this.id_cliente = id_cliente;
	}
	
	
	public Compra(String concepto, Cliente id_cliente) {
		this.concepto = concepto;
		this.id_cliente = id_cliente;
	}
	
	
	// METODOS 
	// GETTER & SETTER 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	

}
