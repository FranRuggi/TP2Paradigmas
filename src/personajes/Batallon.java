package personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import hechizos.Hechizo;
import hechizos.HechizoFactory;

public class Batallon {
	private List<Personaje> personajes = new ArrayList<>();
	private Random rand = new Random();

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
	}

	public boolean tienePersonajesSaludables() {
		return personajes.stream().anyMatch(Personaje::estaSaludable);
	}

	public void atacar(Batallon otroBatallon) {
		for (Personaje atacante : personajes) {
			if (!atacante.estaSaludable() || !atacante.puedeActuar())
				continue; //Salta al siguiente personaje, el actual esta inhabilitado;

			// Seleccionar un personaje enemigo aleatorio
			Personaje objetivo = otroBatallon.obtenerPersonajeSaludable();
			if (objetivo != null) {
				// Seleccionar un hechizo aleatorio
				
				//Creo que seria mejor poner las querys y cosas de prolog en un metodo diferente, tal vez privado que solo lo use
				//el metodo atacar, simplemente para separar un poco la logica y las responsabilidades de cada metodo
				
				// queryConection lo necesitamos para poder consultar a la base de datos de prolog. Es la coneccion
				Query queryConection = new Query("consult",new Term[] {new Atom("MagosVsMortifagosV2.pl")});
		    	queryConection.hasSolution();
		    	
				// Construimos la consulta para Prolog con nivel de magia y tipo de personaje
		        																				//Esta parte se podria evitar si directamente escribimos en minuscula los nombres, pero no es tan grave
				String queryStr = "hechizos_disponibles(" + atacante.getNivelMagia() + ", " + atacante.getTipo().toString().toLowerCase() + ", Hechizos)";
		        Query queryHechizosDisponibles = new Query(queryStr);
		        //Con queryHechizosDisponibles, prolog nos devuelve una lista de hechizos disponibles que tiene nuestro personaje en el momento de atacar
		        List<String> hechizos = new ArrayList<>();
		        if (queryHechizosDisponibles.hasSolution()) {
		        	Term hechizosTerm = queryHechizosDisponibles.oneSolution().get("Hechizos");
		            Term[] soluciones = Term.listToTermArray(hechizosTerm);
		            
		            for (Term hechizo : soluciones) {
		                hechizos.add(hechizo.name());
		            }
		        }
		        //Tenemos la lista preparada para decidir que hechizo crear en base a la disponibilidad del personaje atacante
		        
		        //En este momento deberiamos ser capaces de elegir de forma random un hechizo dentro de los disponibles que tiene el atacante
		        
		        rand.nextInt((hechizos.size()));
				Hechizo hechizo = HechizoFactory.crearHechizo(atacante.getTipo());
				boolean hechizoEjecutado = hechizo.ejecutar(atacante, objetivo); // Ejecutar hechizo

				if (!hechizoEjecutado) {
					System.out.println(atacante.getNombre() + " no tiene suficientes puntos de magia para lanzar "
							+ hechizo.obtenerNombre() + ".");
				}

				// Eliminar personajes con puntos de vida en cero
				otroBatallon.eliminarPersonajesInactivos();
			}
		}
	}

	public void recuperarMagia() {
		for (Personaje personaje : personajes) {
			personaje.incrementarNivelMagia(rand.nextInt(0, 20));
		}
	}

	private Personaje obtenerPersonajeSaludable() {
		List<Personaje> personajesSaludables = new ArrayList<>();
		for (Personaje personaje : personajes) {
			if (personaje.estaSaludable()) {
				personajesSaludables.add(personaje);
			}
		}
		return personajesSaludables.isEmpty() ? null
				: personajesSaludables.get(rand.nextInt(personajesSaludables.size()));
	}

	private void eliminarPersonajesInactivos() {
		personajes.removeIf(personaje -> !personaje.estaSaludable());
	}
}
