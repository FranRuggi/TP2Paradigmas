package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PersonajeFactoryTest {

	@Test
	void testCrearMago_RetornaTipoCorrecto() {
		Personaje mago = PersonajeFactory.crearMago();
		assertTrue(mago instanceof Mago);
	}

	@Test
	void testCrearMortifago_RetornaTipoCorrecto() {
		Personaje mortifago = PersonajeFactory.crearMortifago();
		assertTrue(mortifago instanceof Mortifago);
	}

}
