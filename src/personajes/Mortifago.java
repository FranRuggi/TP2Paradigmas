package personajes;

import hechizos.Hechizo;
import pociones.Pocion;

public class Mortifago extends Personaje {

	public Mortifago(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}

	@Override
	public boolean lanzarHechizo(Personaje objetivo, Hechizo hechizo) {
		return hechizo.ejecutar(this, objetivo);
	}

	@Override
	public TipoPersonaje getTipo() {
		return TipoPersonaje.MORTIFAGO;
	}

	@Override
	public void lanzarPocion(Personaje objetivo, Pocion pocion) {
		pocion.aplicarEfecto(objetivo);
	}
}
