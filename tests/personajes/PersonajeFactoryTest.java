package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

class PersonajeFactoryTest {
	PersonajeFactory fabricaDePersonajes;
    @BeforeEach
    void setUp() {
    	// Reinicia las listas de nombres
    	fabricaDePersonajes = new PersonajeFactory();
    }
    
    @Test
    void testCrearMago() {
        Personaje mago = fabricaDePersonajes.crearMago();
        assertTrue((mago instanceof Auror) || (mago instanceof Estudiante) || (mago instanceof Profesor), "Debería crear un Mago");
    }
    
    @Test
    void testCrearMortifago() {
        Personaje mortifago = fabricaDePersonajes.crearMortifago();
        assertTrue((mortifago instanceof Comandante) || (mortifago instanceof Seguidor), "Deberia crear un Mortifago");
    }
    
    @Test
    void testCrearMagoSinNombres() {
    	fabricaDePersonajes.vaciarListaNombresMagos(); // Vacía la lista de nombres de magos
        Exception exception = assertThrows(IllegalStateException.class, () -> {
        	fabricaDePersonajes.crearMago();
        });
        assertEquals("No hay más nombres disponibles para magos.", exception.getMessage());
    }

    @Test
    void testCrearMortifagoSinNombres() {
    	fabricaDePersonajes.vaciarListaNombresMortifagos(); // Vacía la lista de nombres de mortífagos
        Exception exception = assertThrows(IllegalStateException.class, () -> {
        	fabricaDePersonajes.crearMortifago();
        });
        assertEquals("No hay más nombres disponibles para mortífagos.", exception.getMessage());
    }
}
