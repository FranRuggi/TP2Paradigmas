package personajes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.Expelliarmus;
import hechizos.Hechizo;

class BatallonTest {
    private Batallon batallon1;
    private Batallon batallon2;
    private Personaje personajeSaludable;
    private Personaje personajeHerido;


    @BeforeEach
    void setUp() {
        batallon1 = new Batallon();
        batallon2 = new Batallon();

        personajeSaludable = new Mago("Harry", 100, 50);
        personajeHerido = new Mortifago("Voldemort", 10, 60);
    }

    @Test
    void agregarPersonajeTest() {
        batallon1.agregarPersonaje(personajeSaludable);
        assertTrue(batallon1.tienePersonajesSaludables());
    }

    @Test
    void noPersonajesSaludablesInicialmenteTest() {
        assertFalse(batallon1.tienePersonajesSaludables());
    }

    @Test
    void tienePersonajesSaludablesTrueTest() {
        batallon1.agregarPersonaje(personajeSaludable);
        assertTrue(batallon1.tienePersonajesSaludables());
    }

    @Test
    void tienePersonajesSaludablesFalseTest() {
        personajeSaludable.setPuntosVida(0);
        batallon1.agregarPersonaje(personajeSaludable);
        assertFalse(batallon1.tienePersonajesSaludables());
    }

    @Test
    void atacarPersonajeAturdidoTest() {
        personajeSaludable.aturdir();
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        assertFalse(batallon2.tienePersonajesSaludables());
    }

    @Test
    void atacarBatallonVacioTest() {
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        assertTrue(batallon1.tienePersonajesSaludables());
    }

    @Test
    void atacarPersonajeSinRecursosTest() {
        personajeSaludable.setInventarioPociones(0);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        assertTrue(batallon1.tienePersonajesSaludables());
    }

    @Test
    void ataqueReduceVidaEnemigoTest() {
        personajeHerido.setPuntosVida(100);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon2.agregarPersonaje(personajeHerido);
        batallon1.atacar(batallon2);
        assertTrue(personajeHerido.getPuntosVida() < 100);
    }

    @Test
    void lanzarPocionSiNoHayHechizosTest() {
        personajeSaludable.setInventarioPociones(1);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        assertEquals(0, personajeSaludable.getInventarioPociones());
    }

    @Test
    void eliminarPersonajesInactivosTest() {
        personajeHerido.setPuntosVida(0);
        batallon1.agregarPersonaje(personajeHerido);
        batallon1.eliminarPersonajesInactivos();
        assertFalse(batallon1.tienePersonajesSaludables());
    }

    @Test
    void personajesConMagiaRecuperanMagiaTest() {
        personajeSaludable.disminuirNivelMagia(70);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.recuperarMagia();
        assertTrue(personajeSaludable.getNivelMagia() > 30);
    }

    @Test
    void mostrarHechizosLanzadosPorPersonajeTest() {
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2); // Ejecutar algún hechizo
        // Verifica que se imprime el hechizo (se espera que `mostrarHechizosLanzadosPorPersonaje` no lance excepciones)
        batallon1.mostrarHechizosLanzadosPorPersonaje();
    }
/*
    @Test
    void agregarHechizoLanzadoTest() {
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        assertFalse(batallon1.hechizosLanzadosPorPersonaje().isEmpty());
    }

    @Test
    void ataqueNoRepiteHechizoMismaRondaTest() {
        Hechizo primerHechizo = new Expelliarmus();
        Hechizo segundoHechizo = new Expelliarmus();
        batallon1.hechizosLanzadosEquipoRonda.add(primerHechizo);
        assertFalse(batallon1.hechizosLanzadosEquipoRonda.contains(segundoHechizo));
    }*/

    @Test
    void ataqueSinPersonajesNoEjecutaHechizoTest() {
        batallon1.agregarPersonaje(personajeHerido);
        personajeHerido.setPuntosVida(0);
        batallon1.atacar(batallon2);
        assertTrue(batallon2.tienePersonajesSaludables());
    }

    @Test
    void pocionAplicaEfectoCorrectoTest() {
        personajeSaludable.setInventarioPociones(1);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2); // Si no hay hechizos, aplica poción
        assertEquals(0, personajeSaludable.getInventarioPociones());
    }

    @Test
    void eliminarPersonajeInactivoCuandoVidaCeroTest() {
        personajeSaludable.setPuntosVida(0);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.eliminarPersonajesInactivos();
        assertFalse(batallon1.tienePersonajesSaludables());
    }

    @Test
    void personajePuedeRecibirDanioAlAtacarTest() {
        int vidaInicial = personajeHerido.getPuntosVida();
        batallon1.agregarPersonaje(personajeSaludable);
        batallon2.agregarPersonaje(personajeHerido);
        batallon1.atacar(batallon2);
        assertTrue(personajeHerido.getPuntosVida() < vidaInicial);
    }

    @Test
    void ataqueSinRecursosPierdeTurnoTest() {
        personajeSaludable.disminuirNivelMagia(100);
        personajeSaludable.setInventarioPociones(0);
        batallon1.agregarPersonaje(personajeSaludable);
        batallon1.atacar(batallon2);
        // Si no hay hechizos y no hay pociones, debería simplemente perder el turno sin aplicar efectos
        assertEquals(0, personajeSaludable.getInventarioPociones());
    }
}

