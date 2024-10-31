package hechizos;

import personajes.Personaje;

public class AvadaKedavra implements Hechizo {
	
	private static final int COSTO = 100;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.setPuntosVida(0); // Mata instantáneamente
		System.out.println("¡" + objetivo.getNombre() + " ha sido eliminado por Avada Kedavra!");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "AvadaKedavra";
	}
}
