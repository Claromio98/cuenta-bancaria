package edu.unlam.paradigmas.herencia;

public class MovimientoNoValido extends RuntimeException{
	private static final long serialVersionUID = 7763324240983015815L;
	
	public MovimientoNoValido(String message) {
		super(message);
	}
	
}
