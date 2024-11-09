package personajes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import hechizos.Hechizo;
import hechizos.HechizoFactory;

public class Batallon {
	private List<Personaje> personajes = new ArrayList<Personaje>();
	private List<Hechizo> hechizoLanzadoEquipo = new ArrayList<Hechizo>();
	private Random rand = new Random();
	private Set<Hechizo> hechizosLanzadosEquipoRonda = new HashSet<Hechizo>();
	//marca del mapa
	public Map<Personaje,Hechizo> hechizosLanzadosPersonajeRonda = new HashMap<Personaje,Hechizo>();
	
	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
	}

	public boolean tienePersonajesSaludables() {
		return personajes.stream().anyMatch(Personaje::estaSaludable);
	}

	public void atacar(Batallon otroBatallon) {
		hechizosLanzadosEquipoRonda.clear(); //Reiniciamos el set de hechizos lanzados por ronda
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
		        
				// Convertimos hechizosLanzadosEquipo a una lista de nombres de hechizos en formato Prolog
				List<String> nombresHechizosLanzados = hechizosLanzadosEquipoRonda.stream()
				    .map(Hechizo::obtenerNombre)
				    .collect(Collectors.toList());

				String listaHechizosLanzados = nombresHechizosLanzados.toString().replace("[", "[").replace("]", "]");

				// Consulta para prolog
				String queryStr = "hechizos_disponibles(" 
				                    + atacante.getNivelMagia() + ", " 
				                    + atacante.getTipo().toString().toLowerCase() + ", " 
				                    + listaHechizosLanzados + ", Hechizos)";
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
		        if(!hechizos.isEmpty()) {
		        	String hechizo = hechizos.get(rand.nextInt(hechizos.size()));
		        	Hechizo hechizoAEjecutar = HechizoFactory.crearHechizo(hechizo);
		        	//Historial de hechizosLanzados por equipo
		        	this.hechizoLanzadoEquipo.add(hechizoAEjecutar);
		        	hechizoAEjecutar.ejecutar(atacante, objetivo); // Ejecutar hechizo --> Nunca va a poder darnos false, ya que solo se eligen hechizos que puedan ejecutar
		        	//marca del mapa
		        	this.hechizosLanzadosPersonajeRonda.put(atacante,hechizoAEjecutar);
		        	//hechizosLanzadosEquipoRonda utilizada para verificar que no tiren el mismo hechizo en las rondas 
		        	this.hechizosLanzadosEquipoRonda.add(hechizoAEjecutar);
		        } else {
		        	
		        	//Agregar pociones
		        	System.out.println(atacante.getNombre() + " no tiene suficientes puntos de magia para lanzar hechizos, pierde el turno. ");
		        }
		        // Eliminar personajes con puntos de vida en cero
				otroBatallon.eliminarPersonajesInactivos();
			}
		}
	}

	public void recuperarMagia() {
		for (Personaje personaje : personajes) {
			personaje.incrementarNivelMagia(rand.nextInt(20, 50));
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
