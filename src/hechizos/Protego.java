package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class Protego implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {

		Query queryConection = new Query("consult", new Term[] { new Atom("MagosVsMortifagos.pl") });
		queryConection.hasSolution();

		Query queryHechizo = new Query("ejecutarHechizo",
				new Term[] { new Integer(COSTO), new Integer(lanzador.getNivelMagia()) });
		if (!queryHechizo.hasSolution())
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		lanzador.recibirDaño(-20); // Ejemplo de incremento de defensa
		System.out.println("Un escudo mágico protege a " + lanzador.getNombre());
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Protego";
	}
}
