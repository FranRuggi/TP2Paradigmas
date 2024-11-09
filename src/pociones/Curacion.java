package pociones;

import personajes.Personaje;
import personajes.TipoPersonaje;

public class Curacion implements Pocion {

	@Override
	public void aplicarEfecto(Personaje objetivo) {
		if (objetivo.getTipo() == TipoPersonaje.MAGO)
			objetivo.incrementarPuntosVida(55);
		else
			objetivo.incrementarPuntosVida(25);
	}

	@Override
	public String obtenerNombre() {
		return "Curacion";
	}

}
