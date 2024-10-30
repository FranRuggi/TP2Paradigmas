package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class ExpectoPatronum implements Hechizo {
	private static final int COSTO = 30;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
		Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")});
    	queryConection.hasSolution(); 
    	
    	Query queryHechizo = new Query("ejecutarHechizo", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
		lanzador.disminuirNivelMagia(COSTO);
		lanzador.recibirDaño(-10); 
		System.out.println(lanzador.getNombre() + " se cura debido a ExpectoPatronum");
		return true;
	}

	@Override
	public String obtenerNombre() {
		return "ExpectoPatronum";
	}
}
