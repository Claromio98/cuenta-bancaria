package edu.unlam.paradigmas.herencia;

import java.time.LocalDate;

public class TarjetaCredito extends Tarjeta{
	//protected double limiteUnPago;
	//protected double limiteCuotas;
	//protected double acumuladoUnPago = 0;
	//protected double acumuladoCuotas = 0;
	
/*	
	public TarjetaCredito(String nombreTitular, Cuenta cuentaAsociada, double limiteUnPago) {
		super(nombreTitular,cuentaAsociada);
		this.limiteUnPago = limiteUnPago;
	}
	
	public TarjetaCredito(String nombreTitular,Cuenta cuentaAsociada,int cvv,long numTarjeta,LocalDate validaDesde, LocalDate validaHasta, double limiteUnPago, double limiteCuotas) {
		super(nombreTitular, cuentaAsociada, cvv, numTarjeta, validaDesde,  validaHasta);
		this.limiteUnPago = limiteUnPago;
		this.limiteCuotas = limiteCuotas;
	}
*/	
	protected double limite;
	protected double acumulado;
	protected boolean habilitada;

	public TarjetaCredito(String nombreTitular, Cuenta cuentaAsociada, double limite) {
		super(nombreTitular,cuentaAsociada);
		this.limite = limite;
		this.acumulado = 0;
		this.habilitada = true;
	}
	
	public TarjetaCredito(String nombreTitular,Cuenta cuentaAsociada,int cvv,long numTarjeta,LocalDate validaDesde, LocalDate validaHasta, double limite) {
		super(nombreTitular, cuentaAsociada, cvv, numTarjeta, validaDesde,  validaHasta);
		this.limite = limite;
		this.acumulado = 0;
		this.habilitada = true;
	}
	
	public boolean comprar(double montoAPagar) {
		boolean band = false;
		
		if(!this.habilitada || this.validaHasta.isBefore(LocalDate.now())) {
			this.habilitada = false;
		} else if((acumulado + montoAPagar) <= limite){
			band = true;
			acumulado += montoAPagar;
		}
		
		return band;	
	}
	
	
	public boolean consultarEstado() {
		return this.habilitada;
	}
	
	
	public boolean pagarTarjetaCredito() {
		
		try {
			this.cuentaAsociada.extraer(acumulado*1.03);
			acumulado = 0;
		} catch(MovimientoNoValido e) {
			System.out.println("Error al pagar Tarjeta.");
		}
		
		return true;
	}
	
	
	
}
