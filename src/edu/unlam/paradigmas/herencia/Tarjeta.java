package edu.unlam.paradigmas.herencia;
import java.util.Random;
import java.time.LocalDate;

public class Tarjeta {
	protected Cuenta cuentaAsociada;
	protected String nombreTitular;
	protected long numTarjeta;
	protected int cvv;
	protected LocalDate validaDesde;
	protected LocalDate validaHasta;

	
	public Tarjeta(String nombreTitular,Cuenta cuentaAsociada){
		Random rand = new Random();
		
		this.cvv = rand.nextInt(1000);
		this.nombreTitular = nombreTitular;
		this.cuentaAsociada = cuentaAsociada;
		
		this.numTarjeta = rand.nextLong(10000000) + rand.nextLong(10000000);
		this.validaDesde = LocalDate.now();
		this.validaHasta = LocalDate.of(this.validaDesde.getYear() + 7, this.validaDesde.getMonthValue() + 1 , this.validaDesde.getDayOfMonth());
	}
	
	
	public Tarjeta(String nombreTitular,Cuenta cuentaAsociada,int cvv,long numTarjeta,LocalDate validaDesde, LocalDate validaHasta){
		this.cvv = cvv;
		this.nombreTitular = nombreTitular;
		this.cuentaAsociada = cuentaAsociada;
		this.numTarjeta = numTarjeta;
		this.validaDesde = validaDesde;
		this.validaHasta = validaHasta;
	}
	
	public boolean comprar(double montoAPagar) {
		try{
			this.cuentaAsociada.extraer(montoAPagar);
		} catch(MovimientoNoValido mov) {
			System.out.println("No se puede realizar la compra");
		}
		
		
		return true;
	}
	
	
	
	
	public Cuenta consultarCuenta() {
		return this.cuentaAsociada;
	}
	
	public long consultarNumTarjeta() {
		return this.numTarjeta;
	}
	
	protected int consultarCVV() {
		return this.cvv;
	}
	
	public LocalDate consultarFechaInicial() {
		return this.validaDesde;
	}
	
	public LocalDate consultarFechaVencimiento() {
		return this.validaHasta;
	}
	
}
