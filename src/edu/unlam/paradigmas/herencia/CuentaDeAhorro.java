package edu.unlam.paradigmas.herencia;

public class CuentaDeAhorro extends Cuenta{
	protected double saldoSecundario;
	
	public CuentaDeAhorro() {
		saldoSecundario = 0;
	}
	
	public boolean reservarSaldo(double monto) {
		try {
			this.extraer(monto);
			this.saldoSecundario += monto;
			return true;
		} catch(MovimientoNoValido mov) {
			System.out.println("Ocurrió un error al reservar.");
			return false;
		}
	}
	
	public boolean reintegrarSaldo(double monto) {
		if(monto <= this.saldoSecundario && monto >= 0) {
			this.saldo += monto;
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
