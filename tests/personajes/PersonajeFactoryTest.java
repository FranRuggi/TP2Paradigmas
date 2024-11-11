package personajes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
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
    }
    
    @Test
    void testCrearMago() {
        Personaje mago = PersonajeFactory.crearMago();
        assertTrue((mago instanceof Auror) || (mago instanceof Estudiante) || (mago instanceof Profesor), "Debería crear un Mago");
    }
    
    @Test
    void testCrearMortifagoComandante() {
        Personaje mortifago = PersonajeFactory.crearMortifago();
        assertTrue((mortifago instanceof Comandante) || (mortifago instanceof Seguidor), "Deberia crear un Mortifago");
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
    void testCrearMortifagoSinNombres() {
        PersonajeFactory.nombresMortifagos.clear(); // Vacía la lista de nombres de mortífagos
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PersonajeFactory.crearMortifago();
        });
        assertEquals("No hay más nombres disponibles para mortífagos.", exception.getMessage());
    }
}
