package hechizos;

import personajes.Personaje;

public class Stupefy implements Hechizo {
	private static final int COSTO = 30;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.aturdir(); // El objetivo es aturdido temporalmente (pierde un turno)
		System.out.println(objetivo.getNombre() + " ha sido aturdido con Stupefy.");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Stupefy";
	}
}
