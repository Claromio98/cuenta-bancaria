package edu.unlam.paradigmas.herencia;

import java.time.LocalDate;

public class TarjetaDebito extends Tarjeta{
	public TarjetaDebito(String nombreTitular, Cuenta cuentaAsociada) {
		super(nombreTitular,cuentaAsociada);
		
	}
	
	public TarjetaDebito(String nombreTitular,Cuenta cuentaAsociada,int cvv,long numTarjeta,LocalDate validaDesde, LocalDate validaHasta) {
		super(nombreTitular, cuentaAsociada, cvv, numTarjeta, validaDesde,  validaHasta);
		
	}
}