package pociones;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import personajes.Mago;
import personajes.Mortifago;
import personajes.Personaje;

class PocionTest {

	private Personaje mago;
	private Personaje mortifago;

	@BeforeEach
	void setUp() {
		mago = new Mago("Harry", 10, 50); // Inicializamos con valores de prueba
		mortifago = new Mortifago("Voldemort", 5, 30);
	}
	@Test
	void testAplicarEfectoCuracion() {
		Pocion curacion = new Curacion();
		curacion.aplicarEfecto(mago);
		assertEquals(105, mago.getPuntosVida(), "La vida del mago debería ser 105 después de aplicar Curacion.");
	}
	@Test
	void testCuracionParaMagoIncrementaPuntosVida() {
		Pocion curacion = new Curacion();
		mago.lanzarPocion(mago, curacion);
		assertEquals(105, mago.getPuntosVida(), "La vida del mago debería ser 105 después de aplicar Curacion.");
	}

	@Test
	void testCuracionParaMortifagoIncrementaPuntosVida() {
		Pocion curacion = new Curacion();
		mortifago.lanzarPocion(mortifago, curacion);
		assertEquals(55, mortifago.getPuntosVida(),
				"La vida del mortífago debería ser 55 después de aplicar Curacion.");
	}
	
	@Test
	void testCuracionNombreEsCorrecto() {
		Pocion curacion = new Curacion();
		assertEquals("Curacion", curacion.obtenerNombre());
	}
	// Tests para la clase NivelMagia

	@Test
	void testNivelMagiaParaMagoIncrementaNivelMagia() {
		Pocion nivelMagia = new NivelMagia();
		mago.lanzarPocion(mago, nivelMagia);
		assertEquals(40, mago.getNivelMagia(),
				"El nivel de magia del mago debería ser 40 después de aplicar NivelMagia.");
	}

	@Test
	void testNivelMagiaParaMortifagoIncrementaNivelMagia() {
		Pocion nivelMagia = new NivelMagia();
		mortifago.lanzarPocion(mortifago, nivelMagia);
		assertEquals(25, mortifago.getNivelMagia(),
				"El nivel de magia del mortífago debería ser 25 después de aplicar NivelMagia.");
	}

	@Test
	void testNivelMagiaNombreEsCorrecto() {
		Pocion nivelMagia = new NivelMagia();
		assertEquals("NivelDeMagia", nivelMagia.obtenerNombre());
	}

	// Tests para la clase PocionFactory

	@Test
	void testPocionFactoryGeneraCuracion() {
		Pocion pocion = new Curacion();
		assertTrue(pocion instanceof Curacion, "PocionFactory debería crear una instancia de Curacion.");
	}

	@Test
	void testPocionFactoryGeneraNivelMagia() {
		Pocion pocion = new NivelMagia();
		assertTrue(pocion instanceof NivelMagia, "PocionFactory debería crear una instancia de NivelMagia.");
	}

	@RepeatedTest(6)
	void testPocionFactoryGeneraPocionAleatorias() {
		Pocion pocion = PocionFactory.crearPocion();
		assertTrue(pocion instanceof Curacion || pocion instanceof NivelMagia,
				"PocionFactory debería crear Curacion o NivelMagia.");
	}

	@Test
	void testFactoryGeneraAmbosTipos() {
		boolean encontradoCuracion = false;
		boolean encontradoNivelMagia = false;
		for (int i = 0; i < 100; i++) {
			Pocion pocion = PocionFactory.crearPocion();
			if (pocion instanceof Curacion)
				encontradoCuracion = true;
			if (pocion instanceof NivelMagia)
				encontradoNivelMagia = true;
			if (encontradoCuracion && encontradoNivelMagia)
				break;
		}
		assertTrue(encontradoCuracion, "La fábrica no generó Curacion.");
		assertTrue(encontradoNivelMagia, "La fábrica no generó NivelMagia.");
	}
}
