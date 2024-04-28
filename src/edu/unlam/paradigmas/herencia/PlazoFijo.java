package edu.unlam.paradigmas.herencia;
import java.time.LocalDate;

public class PlazoFijo {
	
	protected double capitalReservado;
	protected LocalDate fechaInicio;
	protected Cuenta cuentaAsociada;
	
	
	public PlazoFijo(double capitalReservado, Cuenta cuentaAsociada) {
		this.capitalReservado = capitalReservado;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaInicio = LocalDate.now();
	}
	
	public void reintegrar() {
		if(this.fechaInicio.plusDays(30) == LocalDate.now()) {
			this.cuentaAsociada.depositar(this.capitalReservado * 1.0295890411);
		}
	}
	
	
	
	
}
