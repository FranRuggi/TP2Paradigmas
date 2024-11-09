package hechizos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.Mago;
import personajes.Mortifago;
import personajes.Personaje;

class HechizoTest {

	private Personaje lanzador;
    private Personaje objetivo;

    @BeforeEach
    void setUp() {
        lanzador = new Mago("Harry", 100, 100);
        objetivo = new Mortifago("Bellatrix", 100, 100);
    }

    @Test
    void testAvadaKedavra_MagiaSuficiente_MataObjetivo() {
        Hechizo hechizo = new AvadaKedavra();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(0, objetivo.getPuntosVida());
    }

    @Test
    void testAvadaKedavra_MagiaInsuficiente_NoMataObjetivo() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new AvadaKedavra();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(100, objetivo.getPuntosVida());
    }

    @Test
    void testCrucio_MagiaSuficiente_InfligeDanio() {
        Hechizo hechizo = new Crucio();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(50, objetivo.getPuntosVida());
    }

    @Test
    void testCrucio_MagiaInsuficiente_NoInfligeDanio() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new Crucio();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(100, objetivo.getPuntosVida());
    }

    @Test
    void testExpectoPatronum_MagiaSuficiente_CuraAlLanzador() {
        lanzador.recibirDaño(50); // Lanzador pierde vida
        Hechizo hechizo = new ExpectoPatronum();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(70, lanzador.getPuntosVida());
    }

    @Test
    void testExpectoPatronum_MagiaInsuficiente_NoCuraAlLanzador() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new ExpectoPatronum();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(100, lanzador.getPuntosVida());
    }

    @Test
    void testExpelliarmus_MagiaSuficiente_DesarmaObjetivo() {
        Hechizo hechizo = new Expelliarmus();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertTrue(objetivo.getTurnoPerdido());
    }

    @Test
    void testExpelliarmus_MagiaInsuficiente_NoDesarmaObjetivo() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new Expelliarmus();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertFalse(objetivo.getTurnoPerdido());
    }

    @Test
    void testImperius_MagiaSuficiente_ControlObjetivo() {
        Hechizo hechizo = new Imperius();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(70, objetivo.getPuntosVida());
    }

    @Test
    void testImperius_MagiaInsuficiente_NoControlObjetivo() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new Imperius();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(100, objetivo.getPuntosVida());
    }

    @Test
    void testPetrificusTotalus_MagiaSuficiente_PetrificaObjetivo() {
        Hechizo hechizo = new PetrificusTotalus();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertTrue(objetivo.getTurnoPerdido());
        assertEquals(60, objetivo.getPuntosVida());
    }

    @Test
    void testPetrificusTotalus_MagiaInsuficiente_NoPetrificaObjetivo() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new PetrificusTotalus();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertFalse(objetivo.getTurnoPerdido());
    }

    @Test
    void testProtego_MagiaSuficiente_IncrementaDefensa() {
        Hechizo hechizo = new Protego();
        int puntosDeVidaAntesHechizo = lanzador.getPuntosVida();
        
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertTrue(puntosDeVidaAntesHechizo < lanzador.getPuntosVida()); // Defensa incrementada
    }

    @Test
    void testSectumsempra_MagiaSuficiente_InfligeDanioCritico() {
        Hechizo hechizo = new Sectumsempra();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertEquals(50, objetivo.getPuntosVida());
    }

    @Test
    void testStupefy_MagiaSuficiente_AturdeObjetivo() {
        Hechizo hechizo = new Stupefy();
        assertTrue(hechizo.ejecutar(lanzador, objetivo));
        assertTrue(objetivo.getTurnoPerdido());
    }

    @Test
    void testStupefy_MagiaInsuficiente_NoAturdeObjetivo() {
        lanzador.disminuirNivelMagia(100);
        Hechizo hechizo = new Stupefy();
        assertFalse(hechizo.ejecutar(lanzador, objetivo));
        assertFalse(objetivo.getTurnoPerdido());
    }

}
