package edu.unlam.paradigmas.herencia;

public class CuentaDeAhorro extends Cuenta{
	protected double saldoSecundario;
	
	public CuentaDeAhorro() {
		saldoSecundario = 0;
	}
	
	public synchronized boolean reservarSaldo(double monto) {
		if(this.extraer(monto)) {
			this.saldoSecundario += monto;
			return true;
		}
		
		System.out.println("Ocurrió un error al reservar.");
		return false;
	}
	
	public boolean reintegrarSaldo(double monto) {
		if(monto <= this.saldoSecundario && monto >= 0) {
			this.acreditar(monto);
			this.saldoSecundario -= monto;
			return true;
		} else {
			throw new MovimientoNoValido("No puede realizar este reintegro.");
		}
	}
	
	public double consultarSaldoSecundario() {
		return this.saldoSecundario;
	}

}
