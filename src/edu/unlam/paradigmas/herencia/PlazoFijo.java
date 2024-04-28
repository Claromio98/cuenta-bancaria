package edu.unlam.paradigmas.herencia;
import java.time.LocalDate;


public class PlazoFijo {
	
	public enum TipoRenovacion{
		PARCIAL,TOTAL,NINGUNA
	};
	
	protected double capitalReservado;
	protected LocalDate fechaInicio;
	protected Cuenta cuentaAsociada;
	protected TipoRenovacion tipo;
	
	
	public PlazoFijo(double capitalReservado, Cuenta cuentaAsociada, TipoRenovacion tipo) {
		this.capitalReservado = capitalReservado;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaInicio = LocalDate.now();
		this.tipo = tipo;
	}
	
	public PlazoFijo(double capitalReservado, Cuenta cuentaAsociada) {
		this.capitalReservado = capitalReservado;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaInicio = LocalDate.now();
		this.tipo = TipoRenovacion.NINGUNA;
	}
	
	public void reintegrar() {
		if(!fechaInicio.equals(LocalDate.MAX) && fechaInicio.plusDays(30).isBefore(LocalDate.now()) ) {
			fechaInicio = LocalDate.now();
			switch(this.tipo) {
				case PARCIAL:
					this.cuentaAsociada.depositar(this.capitalReservado * 0.0295890411);///Se depositan unicamente los intereses generados y se mantiene el capital original
					break;
				case TOTAL:
					this.capitalReservado = this.capitalReservado * 1.0295890411; ///Se renueve el capital reservado con los intereses generados. no se deposita nada.
					break;
				case NINGUNA:
					this.cuentaAsociada.depositar(this.capitalReservado * 1.0295890411);//Se deposita el capital reservado con los intereses
					this.fechaInicio = LocalDate.MAX;//la fecha se pone en max para no seguir reintegrando
					break;
			}
		}
	}
	

	
	
	
}
