package personajes;

import hechizos.Hechizo;
import pociones.Pociones;

public class Mago extends Personaje {
	
	public Mago(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}

	@Override
	public boolean lanzarHechizo(Personaje objetivo, Hechizo hechizo) {
		return hechizo.ejecutar(this, objetivo);
	}

	@Override
	public TipoPersonaje getTipo() {
		return TipoPersonaje.MAGO;
	}

	@Override
	public void lanzarPocion(Personaje objetivo, Pociones pocion) {
		pocion.aplicarEfecto(objetivo);
	}

}
