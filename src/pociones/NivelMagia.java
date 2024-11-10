package pociones;

import personajes.Personaje;
import personajes.TipoPersonaje;

public class NivelMagia implements Pocion {

	@Override
	public void aplicarEfecto(Personaje objetivo) {
		if (objetivo.getTipo() == TipoPersonaje.MAGO)
			objetivo.incrementarNivelMagia(30);
		else
			objetivo.incrementarNivelMagia(20);
		System.out.println(objetivo.getNombre() + " a lanzado una pocion de NivelMagia, ahora tiene: " + objetivo.getNivelMagia() + " puntos de magia"
				+ ", le quedan " + objetivo.getInventarioPociones()+ " pociones.");

	}

	@Override
	public String obtenerNombre() {
		return "NivelDeMagia";
	}
}
