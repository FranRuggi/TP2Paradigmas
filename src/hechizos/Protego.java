package hechizos;

import personajes.Personaje;

public class Protego implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		lanzador.recibirDaño(-20); // Ejemplo de incremento de defensa
		System.out.println("Un escudo mágico protege a " + lanzador.getNombre()); // Cambio en lanzador
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Protego";
	}
}
