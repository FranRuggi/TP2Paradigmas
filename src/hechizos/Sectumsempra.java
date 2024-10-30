package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class Sectumsempra implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
    	
		Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")});
    	queryConection.hasSolution(); 
    	/* queryConection.hasSolution();
    	 * No deberia dar nunca error ya que el archivo esta incluido en el proyecto
    	 * A su vez, la linea tiene que estar ya que si no se evalua el resultado de la query genera error.
    	 */
    	
    	
    	Query queryHechizo = new Query("ejecutarHechizo", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.recibirDaño(50); // Aplica un daño oscuro y crítico
		System.out.println(lanzador.getNombre() + "Aplica un daño oscuro y critico a " + objetivo.getNombre());
		return true;
	}
	/*
	 * Ideas de prolog para reemplazar logica de verifiacion de lanzamiento de hechizos
	 * ejecutar(COSTOHECHIZO,MAGIALANZADOR) : -
	 * 			MAGIALANZADOR >= COSTOHECHIZO.
	 * hechizo(SECTUMSEMPRA) :-
	 * 		   			
	 */
	

	@Override
	public String obtenerNombre() {
		return "Sectumsempra";
	}
}
