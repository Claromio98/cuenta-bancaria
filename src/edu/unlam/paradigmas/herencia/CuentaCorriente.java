package edu.unlam.paradigmas.herencia;

public class CuentaCorriente extends Cuenta{
	protected double descubierto;
	public CuentaCorriente(double descubierto) {
		setDescubierto(descubierto);
	}
	
	public boolean extraer(double dinero) {
		if((this.saldo + this.descubierto) < dinero || dinero < 0) {
			throw new MovimientoNoValido("No puede realizar esta extracción.");
		}
		this.saldo -= dinero;
		return true;
	}
	
	public void setDescubierto(double descubierto) {
		if(descubierto < 0) {
			throw new MovimientoNoValido("Descubierto inválido.");
		}
		this.descubierto = descubierto;
	}
	
	
	public double consultarDescubierto() {
		return this.descubierto;
	}
	
}
