package hechizos;

import personajes.Personaje;

public class Expelliarmus implements Hechizo {
	private static final int COSTO = 25;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.desarmar(); // El personaje pierde un turno
		objetivo.recibirDaño(60);
		System.out.println(objetivo.getNombre() + " ha sido desarmado con Expelliarmus!");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Expelliarmus";
	}
}
