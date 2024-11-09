package hechizos;

import personajes.Personaje;

public class Crucio implements Hechizo {
	private static final int COSTO = 50;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.recibirDaño(50);
		System.out.println(objetivo.getNombre() + " sufre de un dolor insoportable debido a Cruciatus.");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Crucio";
	}
}
