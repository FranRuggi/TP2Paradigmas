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
	}

	@Override
	public String obtenerNombre() {
		return "NivelDeMagia";
	}
}
