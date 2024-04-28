package edu.unlam.paradigmas.herencia;

import java.util.ArrayList;

public class Cuenta {
	protected double saldo;
	protected ArrayList<Transaccion> registro = new ArrayList<>();
	
	public Cuenta() {
		saldo = 0;
	}
	
	public double consultarSaldo() {
		return this.saldo;
	}
	
	public void registrarMovimiento(double monto, String motivo) {
		Transaccion trans = new Transaccion(motivo, monto);
		registro.add(trans);
	}
	
	public boolean depositar(double monto) {
		if(monto < 0 ) {
			throw new MovimientoNoValido("No puede realizar este depósito.");
		}
		saldo += monto;
		registrarMovimiento(monto,"Depósito");
		return true;
	}
	
	public boolean depositar(double monto, String motivo) {
		if(monto < 0 ) {
			throw new MovimientoNoValido("No puede realizar este depósito.");
		}
		saldo += monto;
		registrarMovimiento(monto,motivo);
		return true;
	}
	
	public boolean extraer(double monto) {
		if(this.saldo < monto || monto < 0) {
			throw new MovimientoNoValido("No puede realizar esta extracción.");
		}
		
		this.saldo -= monto;
		registrarMovimiento(monto,"Extraccion");
		
		return true;
	}
	
	public boolean extraer(double monto, String motivo) {
		if(this.saldo < monto || monto < 0) {
			throw new MovimientoNoValido("No puede realizar esta extracción.");
		}
		
		this.saldo -= monto;
		registrarMovimiento(monto,motivo);
		
		return true;
	}
	
	
	public boolean transferir(double dinero, Cuenta cuentaDestino) {
		try {
			this.extraer(dinero, "Transferencia-Extracción");
			cuentaDestino.depositar(dinero, "Transferencia-Depósito");
			return true;
		} catch (MovimientoNoValido mov) {
			System.out.println("Ocurrió un error al transferir. "+ mov.getMessage());///investigar logger y log4j
			return false;
		}
	}
	
	public boolean transferir(double dinero, Cuenta cuentaDestino, String motivo) {
		try {
			this.extraer(dinero, motivo);
			cuentaDestino.depositar(dinero, motivo);
			return true;
		} catch (MovimientoNoValido mov) {
			System.out.println("Ocurrió un error al transferir. "+ mov.getMessage());///investigar logger y log4j
			return false;
		}
	}
	
	public void consultarRegistro() {
		System.out.println("------------------------REGISTRO-----------------------------");
		for(Transaccion trans : registro) {
			///System.out.println(trans);
			trans.consultarTransaccion();
		}
		System.out.println("-------------------------------------------------------------");
	}
	
	public Tarjeta obtieneTarjeta(String nombreTitular) {
		Tarjeta tarjetaCuenta = new Tarjeta(nombreTitular, this);
		return tarjetaCuenta;
	}

}
