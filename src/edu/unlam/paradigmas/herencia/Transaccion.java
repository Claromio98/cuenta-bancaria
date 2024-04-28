package edu.unlam.paradigmas.herencia;
import java.util.Date;

public class Transaccion {
	protected String motivo;
	protected double monto;
	protected Date fecha;
	
	
	public Transaccion(String motivo, double monto) {
		this.motivo = motivo;
		this.monto = monto;
		this.fecha = new Date();
	}
	
	public void consultarTransaccion() {
		System.out.println(this.fecha.toString() + " " + this.motivo + " "  + "$" +this.monto);
	}
	
	@Override
    public String toString() {
        return "Transaccion{" +
        		"fecha='" + fecha + '\'' +
                ", motivo='" + motivo + '\'' +
                ", monto=$" + monto +
                '}';
    }
}
