package hechizos;

import org.jpl7.Atom;
import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;

import personajes.Personaje;

public class Sectumsempra2 implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
    	
		Query queryConexion = new Query("consult",new Term[] {new Atom("MagosVsMortifagos.pl")}); ///Verificamos que podamos consultar las base de Prolog
		queryConexion.hasSolution();
		/* Dejo todo este bloque comentado porque como el archivo MagosVsMortifagos.pl esta en el proyecto, no va a fallar nunca
		 * No tiene sentido hacer todo lo que esta comentado aca abajo
		 * if(!queryConexion.hasSolution())
		 * return false; //Se me ocurre que podriamos hacerlo con algun tipo de excepcion 
    	    //throw new IllegalArgumentException("Archivo no encontrado");
		 */
    		
		Query queryHechizo = new Query("ejecutar", new Term[] {new Integer(COSTO), new Integer(lanzador.getNivelMagia())});
    	if(!queryHechizo.hasSolution())
    		return false;
    	lanzador.disminuirNivelMagia(COSTO);
    	objetivo.recibirDaño(50);// Aplica un daño oscuro y crítico
    	
    	// return true; --> lo comento para que no tire error el eclipse, pero deberia quedar descomentado
    	
		if (lanzador.getNivelMagia() < COSTO)
			return false;
		lanzador.disminuirNivelMagia(COSTO);
		objetivo.recibirDaño(50);// Aplica un daño oscuro y crítico
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