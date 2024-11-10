package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HechizoFactoryTest {

    @Test
    void testCrearProtego() {
        Hechizo hechizo = HechizoFactory.crearHechizo("protego");
        assertTrue(hechizo instanceof Protego);
    }

    @Test
    void testCrearExpelliarmus() {
        Hechizo hechizo = HechizoFactory.crearHechizo("expelliarmus");
        assertTrue(hechizo instanceof Expelliarmus);
    }

    @Test
    void testCrearStupefy() {
        Hechizo hechizo = HechizoFactory.crearHechizo("stupefy");
        assertTrue(hechizo instanceof Stupefy);
    }

    @Test
    void testCrearPetrificusTotalus() {
        Hechizo hechizo = HechizoFactory.crearHechizo("petrificustotalus");
        assertTrue(hechizo instanceof PetrificusTotalus);
    }

    @Test
    void testCrearExpectoPatronum() {
        Hechizo hechizo = HechizoFactory.crearHechizo("expectopatronum");
        assertTrue(hechizo instanceof ExpectoPatronum);
    }

    @Test
    void testCrearImperius() {
        Hechizo hechizo = HechizoFactory.crearHechizo("imperius");
        assertTrue(hechizo instanceof Imperius);
    }

    @Test
    void testCrearCrucio() {
        Hechizo hechizo = HechizoFactory.crearHechizo("crucio");
        assertTrue(hechizo instanceof Crucio);
    }

    @Test
    void testCrearAvadaKedavra() {
        Hechizo hechizo = HechizoFactory.crearHechizo("avadakedavra");
        assertTrue(hechizo instanceof AvadaKedavra);
    }

    @Test
    void testCrearSectumsempra() {
        Hechizo hechizo = HechizoFactory.crearHechizo("sectumsempra");
        assertTrue(hechizo instanceof Sectumsempra);
    }

    @Test
    void testCrearHechizoDesconocido() {
        Exception exception = assertThrows(IllegalArgumentException.class, 
        		() -> {HechizoFactory.crearHechizo("hechizoDesconocido");});
        assertEquals("Nombre de hechizo desconocido: hechizoDesconocido", exception.getMessage());
    }
}
