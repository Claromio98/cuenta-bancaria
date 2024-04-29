package edu.unlam.paradigmas.herencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class testCuenta {

	Cuenta miCuenta;
	CuentaCorriente miCuentaCorriente;
	CuentaDeAhorro miCuentaDeAhorro;
	
	@BeforeEach
	public void setup() {
		miCuenta = new Cuenta();
		miCuentaCorriente = new CuentaCorriente(500);
		miCuentaDeAhorro = new CuentaDeAhorro();
	}
	
	
	@Test
	void testCrearCuenta() {
		assertEquals(0,miCuenta.consultarSaldo());
	}
	
	@Test
	void testDepositarExtraerCuenta() {
		miCuenta.depositar(500); //saldo = 0 + 500 = 500
		assertEquals(500,miCuenta.consultarSaldo()); //saldo = 500
		miCuenta.extraer(200); //saldo = 500 - 200 = 300
		assertEquals(300,miCuenta.consultarSaldo()); //saldo = 300
		miCuenta.depositar(1000);
		assertEquals(1300,miCuenta.consultarSaldo());
		
		
		
		
		miCuenta.consultarRegistro();

	}
	
	@Test
	void testDepositoNoValido() {
	    assertThrows(
	      MovimientoNoValido.class, 
	      () -> {
	          miCuenta.montoValido(-200);
	      }
	    );
	    
	    assertFalse(miCuenta.depositar(-200));
	}

	@Test
	void testExtraerNoValido() {
		miCuenta.depositar(100);
	    
		assertThrows(
	      MovimientoNoValido.class, 
	      () -> {
	          miCuenta.saldoDisponible(200);
	      }
	    );
	    
		assertFalse(miCuenta.extraer(200));
		assertFalse(miCuenta.extraer(-200));
	}
	
	@Test
	void testTransferencia() {
		Cuenta cuentaDestino = new Cuenta();
		miCuenta.depositar(10000);
		
		assertEquals(10000,miCuenta.consultarSaldo());
		assertEquals(0,cuentaDestino.consultarSaldo());
		
		assertTrue(miCuenta.transferir(550, cuentaDestino));
		
		assertEquals(9450,miCuenta.consultarSaldo());
		assertEquals(550,cuentaDestino.consultarSaldo());
	}
	
	@Test
	void testTransferenciaNoValida() {
		Cuenta cuentaDestino = new Cuenta();
		miCuenta.depositar(450);
		
		assertEquals(450,miCuenta.consultarSaldo());
		assertEquals(0,cuentaDestino.consultarSaldo());
		
		assertFalse(cuentaDestino.transferir(550,miCuenta));
		
		assertEquals(450,miCuenta.consultarSaldo());
		assertEquals(0,cuentaDestino.consultarSaldo());
	}
	
	@Test
	void testCuentaCorriente(){
		miCuentaCorriente.depositar(1000);
		assertEquals(1000,miCuentaCorriente.consultarSaldo());
		
		miCuentaCorriente.extraer(1250);
		assertEquals(-250,miCuentaCorriente.consultarSaldo());
	}
	
	@Test
	void testCuentaCorrienteExtraerNoValido(){
		assertEquals(500,miCuentaCorriente.consultarDescubierto());
		assertThrows(
			      MovimientoNoValido.class, 
			      () -> {
			          miCuentaCorriente.extraer(-200);
			      }
		);
		
		assertThrows(
			      MovimientoNoValido.class, 
			      () -> {
			          miCuentaCorriente.extraer(800);///Se extrae un valor mayor al del descubierto, no hay dinero depositado en la cuenta
			      }
		);
	}
	
	@Test
	void testCuentaDeAhorro(){
		miCuentaDeAhorro.depositar(2500);
		assertEquals(2500,miCuentaDeAhorro.consultarSaldo());
		assertEquals(0,miCuentaDeAhorro.consultarSaldoSecundario());
		
		miCuentaDeAhorro.reservarSaldo(1500);
		assertEquals(1000,miCuentaDeAhorro.consultarSaldo());
		assertEquals(1500,miCuentaDeAhorro.consultarSaldoSecundario());
		
		miCuentaDeAhorro.reintegrarSaldo(1000);
		assertEquals(2000,miCuentaDeAhorro.consultarSaldo());
		assertEquals(500,miCuentaDeAhorro.consultarSaldoSecundario());
		
		
		assertThrows(
				MovimientoNoValido.class,
				()->{
					miCuentaDeAhorro.reintegrarSaldo(1000);///Integro reintegrar más saldo del que tengo ahorrado en la secundaria.
				}
		);
		
		assertThrows(
				MovimientoNoValido.class,
				()->{
					miCuentaDeAhorro.reintegrarSaldo(-1000);///Intento reintegrar saldo negativo
				}
		);
		
		
		assertFalse(miCuentaDeAhorro.reservarSaldo(3000));///Integro reservar más saldo del que tengo en la cuenta.
		assertFalse(miCuentaDeAhorro.reservarSaldo(-3000));
	}
	
	@Test
	void testTarjeta() {
		Tarjeta prueba = new Tarjeta("Santiago Ja Alvarez", miCuenta, 457, 1338500009, LocalDate.of(2024, 4, 25), LocalDate.of(2031, 4, 25));
		miCuenta.obtieneTarjeta("Santiago Ja Alvarez");
		
		assertEquals(miCuenta,prueba.consultarCuenta());
		assertEquals(1338500009,prueba.consultarNumTarjeta());
		assertEquals(457,prueba.consultarCVV());
		assertEquals(LocalDate.of(2024, 4, 25), prueba.consultarFechaInicial());
		assertEquals(LocalDate.of(2031, 4, 25), prueba.consultarFechaVencimiento());
		
		
	}
	
	
	
	
}
