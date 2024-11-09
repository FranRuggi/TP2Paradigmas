package personajes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Crucio;
import hechizos.Hechizo;

class PersonajeTest {
    
    private Personaje mago;
    private Personaje mortifago;

    @BeforeEach
    void setUp() {
        mago = new Mago("Harry", 100, 100);
        mortifago = new Mortifago("Voldemort", 120, 100);
    }

    @Test
    void testPersonaje_RecibirDanio_VidaBajaCorrectamente() {
        mago.recibirDaño(30);
        assertEquals(70, mago.getPuntosVida());
    }

    @Test
    void testPersonaje_RecibirDanio_VidaNoBajaMenosDeCero() {
        mago.recibirDaño(150);
        assertEquals(0, mago.getPuntosVida());
    }

    @Test
    void testPersonaje_DisminuirNivelMagia_SeReduceCorrectamente() {
        mago.disminuirNivelMagia(30);
        assertEquals(70, mago.getNivelMagia());
    }

    @Test
    void testPersonaje_DisminuirNivelMagia_NoSePuedeBajarMenosDeCero() {
        mago.disminuirNivelMagia(150);
        assertEquals(0, mago.getNivelMagia());
    }

    @Test
    void testPersonaje_PuedeActuar_SiNoPierdeTurno() {
        assertTrue(mago.puedeActuar());
    }

    @Test
    void testPersonaje_PuedeActuar_SiPierdeTurno() {
        mago.desarmar();
        assertFalse(mago.puedeActuar());
    }

    @Test
    void testMago_LanzarHechizo_SiHechizoEjecutado() {
        Hechizo hechizo = new AvadaKedavra();
        assertTrue(mago.lanzarHechizo(mortifago, hechizo));
    }

    @Test
    void testMortifago_LanzarHechizo_SiHechizoEjecutado() {
        Hechizo hechizo = new Crucio();
        assertTrue(mortifago.lanzarHechizo(mago, hechizo));
    }
}
