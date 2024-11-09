package hechizos;

import personajes.Personaje;

public class Sectumsempra implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.recibirDaño(50);
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Sectumsempra";
	}
}