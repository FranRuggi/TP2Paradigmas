package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class Stupefy implements Hechizo {
	private static final int COSTO = 30;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")});
    	queryConection.hasSolution(); 
    	
    	Query queryHechizo = new Query("ejecutarHechizo", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.aturdir(); // El objetivo es aturdido temporalmente (pierde un turno)
		System.out.println(lanzador.getNombre() + " aturdió a " + objetivo.getNombre() + " con Stupefy.");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "Stupefy";
	}
}
