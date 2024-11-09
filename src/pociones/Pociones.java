package pociones;

import personajes.Personaje;

public interface Pociones {

    void aplicarEfecto(Personaje objetivo); // Para pociones que requieren un objetivo adicional
    String obtenerNombre();
}