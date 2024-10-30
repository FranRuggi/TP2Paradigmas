package hechizos;

import personajes.Personaje;

public interface Hechizo {
	boolean ejecutar(Personaje lanzador, Personaje objetivo);
	String obtenerNombre();
}
