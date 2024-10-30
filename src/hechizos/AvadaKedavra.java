package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class AvadaKedavra implements Hechizo {
	private static final int COSTO = 200;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")});
    	queryConection.hasSolution(); 
    	
    	Query queryHechizo = new Query("ejecutarHechizo", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.setPuntosVida(0); // Mata instantáneamente
		System.out.println("¡"+ lanzador.getNombre() + " a eliminado a " + objetivo.getNombre() + " por Avada Kedavra!");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "AvadaKedavra";
	}
}
