package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class PetrificusTotalus implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")});
    	queryConection.hasSolution(); 
    	
    	Query queryHechizo = new Query("ejecutarHechizo", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.petrificar(); // Bloquea acciones del objetivo temporalmente
		objetivo.recibirDaño(40);
		System.out.println(lanzador.getNombre() + " petrificó a " + objetivo.getNombre() + " con Petrificus Totalus.");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "PetrificusTotalus";
	}
}
