package hechizos;

import personajes.Personaje;

public class Imperius implements Hechizo {
	private static final int COSTO = 40;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.recibirDaño(30); // Controla al objetivo temporalmente
		System.out.println(objetivo.getNombre() + " ha caído bajo el control de Imperius.");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Imperius";
	}
}
