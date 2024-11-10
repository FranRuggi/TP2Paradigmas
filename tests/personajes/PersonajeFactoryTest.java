package personajes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

class PersonajeFactoryTest {

    @BeforeEach
    void setUp() {
        // Reinicia las listas de nombres y la instancia de Random antes de cada prueba
        PersonajeFactory.nombresMagos = new ArrayList<>(List.of("Harry", "Hermione", "Ron"));
        PersonajeFactory.nombresMortifagos = new ArrayList<>(List.of("Bellatrix", "Lucius"));
        PersonajeFactory.rand = new Random(0); // Semilla fija para resultados consistentes en pruebas
    }

    @Test
    void testCrearMagoAuror() {
        PersonajeFactory.rand = new Random(0); // Asegura que el primer mago sea un Auror
        Personaje mago = PersonajeFactory.crearMago();
        assertTrue(mago instanceof Auror, "Debería crear un Auror");
    }

    @Test
    void testCrearMagoEstudiante() {
        PersonajeFactory.rand = new Random(1); // Asegura que el segundo mago sea un Estudiante
        Personaje mago = PersonajeFactory.crearMago();
        assertTrue(mago instanceof Estudiante, "Debería crear un Estudiante");
    }

    @Test
    void testCrearMagoProfesor() {
        PersonajeFactory.rand = new Random(2); // Asegura que el tercer mago sea un Profesor
        Personaje mago = PersonajeFactory.crearMago();
        assertTrue(mago instanceof Profesor, "Debería crear un Profesor");
    }

    @Test
    void testCrearMagoSinNombres() {
        PersonajeFactory.nombresMagos.clear(); // Vacía la lista de nombres de magos
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PersonajeFactory.crearMago();
        });
        assertEquals("No hay más nombres disponibles para magos.", exception.getMessage());
    }

    @Test
    void testCrearMortifagoComandante() {
        PersonajeFactory.rand = new Random(0); 
        Personaje mortifago = PersonajeFactory.crearMortifago();
        assertTrue(mortifago instanceof Comandante);
    }

    @Test
    void testCrearMortifagoSeguidor() {
        PersonajeFactory.rand = new Random(1); // Asegura que el segundo mortífago sea un Seguidor
        Personaje mortifago = PersonajeFactory.crearMortifago();
        assertTrue(mortifago instanceof Seguidor);
    }

    @Test
    void testCrearMortifagoSinNombres() {
        PersonajeFactory.nombresMortifagos.clear(); // Vacía la lista de nombres de mortífagos
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PersonajeFactory.crearMortifago();
        });
        assertEquals("No hay más nombres disponibles para mortífagos.", exception.getMessage());
    }
}
