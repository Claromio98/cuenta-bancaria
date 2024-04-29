package edu.unlam.paradigmas.herencia;

import java.util.ArrayList;

import edu.unlam.paradigmas.herencia.PlazoFijo.TipoRenovacion;

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
	
	public void montoValido(double monto) {
		if(monto < 0) {
			throw new MovimientoNoValido("Monto no valido.");
		}
	}
	
	public void saldoDisponible(double monto) {
		if(this.saldo < monto) {
			throw new MovimientoNoValido("No puede realizar esta extracción.");
		}
	}
	
	public void acreditar(double monto) {
		saldo += monto;
	}
	
	public void debitar(double monto) {
		saldo -= monto;
	}
	
	public boolean depositar(double monto) {
		try {
			montoValido(monto);
			acreditar(monto);
			registrarMovimiento(monto,"Depósito");
		}catch(MovimientoNoValido e) {
			e.getMessage();
			return false;
		}
		return true;
	}
	
	public boolean depositar(double monto, String motivo) {
		try {
			montoValido(monto);
			acreditar(monto);
			registrarMovimiento(monto,motivo);
		}catch(MovimientoNoValido e) {
			e.getMessage();
			return false;
		}
		
		return true;
	}
	
	public boolean extraer(double monto) {
		try {
			montoValido(monto);
			saldoDisponible(monto);
			
			debitar(monto);
			registrarMovimiento(monto,"Extraccion");
		}catch(MovimientoNoValido e) {
			e.getMessage();
			return false;
		}
		
		return true;
	}
	
	public boolean extraer(double monto, String motivo) {
		try {
			montoValido(monto);
			saldoDisponible(monto);
			
			debitar(monto);
			registrarMovimiento(monto,motivo);
		}catch(MovimientoNoValido e) {
			e.getMessage();
			return false;
		}
		
		return true;
	}
	
	
	public synchronized boolean transferir(double dinero, Cuenta cuentaDestino) {
		
		if(this.extraer(dinero, "Transferencia-Extracción")) {
			cuentaDestino.acreditar(dinero);
			cuentaDestino.registrarMovimiento(dinero, "Transferencia-Depósito");
			return true;
		}
		return false;
		

	}
	
	public synchronized boolean transferir(double dinero, Cuenta cuentaDestino, String motivo) {
		if(this.extraer(dinero, motivo)) {
			cuentaDestino.acreditar(dinero);
			cuentaDestino.registrarMovimiento(dinero, motivo);
			return true;
		}
		
		return false;
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
	
	public TarjetaCredito obtieneTarjetaCredito(String nombreTitular, double limite) {
		TarjetaCredito tarjetaCuenta = new TarjetaCredito(nombreTitular, this, limite);
		return tarjetaCuenta;
	}
	
	public TarjetaDebito obtieneTarjetaDebito(String nombreTitular) {
		TarjetaDebito tarjetaCuenta = new TarjetaDebito(nombreTitular, this);
		return tarjetaCuenta;
	}
	
	public PlazoFijo abrirPlazoFijo(double capitalReservado) {
		PlazoFijo plazo = new PlazoFijo(capitalReservado, this);
		return plazo;
	}
	
	public PlazoFijo abrirPlazoFijo(double capitalReservado, TipoRenovacion tipo) {
		PlazoFijo plazo = new PlazoFijo(capitalReservado, this, tipo);
		return plazo;
	}
	

}
