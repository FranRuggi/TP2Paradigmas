package personajes;

import java.util.*;
import java.util.stream.Collectors;
import org.jpl7.*;

import hechizos.Hechizo;
import hechizos.HechizoFactory;
import pociones.Pocion;
import pociones.PocionFactory;

public class Batallon {
	private List<Personaje> personajes = new ArrayList<>();
	private List<Hechizo> hechizosLanzadosEquipo = new ArrayList<>();
	private Random rand = new Random();
	private Set<Hechizo> hechizosLanzadosEquipoRonda = new HashSet<>();
	private Map<Personaje, List<Hechizo>> hechizosLanzadosPorPersonaje = new HashMap<>();

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
	}

	public boolean tienePersonajesSaludables() {
		return personajes.stream().anyMatch(Personaje::estaSaludable);
	}

	public void atacar(Batallon otroBatallon) {
		
		// Reiniciamos set hechizos lanzados en esta ronda para tener posibilidad de todos los hechizos
		hechizosLanzadosEquipoRonda.clear(); 
		for (Personaje atacante : personajes) {
			if (!atacante.estaSaludable() || !atacante.puedeActuar()) continue; 
			
			// Si no esta saludable o no puede actuar, sigue al siguiente personaje
			Personaje objetivo = otroBatallon.obtenerPersonajeSaludable();
			if (objetivo == null) continue;

			//Consultamos Hechizos posibles para ejecutar
			List<String> hechizosDisponibles = consultarHechizosDisponibles(atacante);
			
			//Si tiene hechizos disponibles
			
			if (!hechizosDisponibles.isEmpty()) {
				Hechizo hechizo = seleccionarHechizoDisponible(hechizosDisponibles); //Selecciona hechizo random
				this.ejecutarHechizo(atacante, objetivo, hechizo);
				//Si no tiene hechizos disponibles, busca pociones
			} else if (atacante.getInventarioPociones() > 0) {
				lanzarPocion(atacante); //Lanza una pocion random
				//En caso de que no tenga ni hechizos ni pociones, pierde el turno
			} else {
				System.out.println(atacante.getNombre() + " pierde el turno por falta de recursos");
			}
			//Eliminamos los personajes inactivos (sin vida);
			otroBatallon.eliminarPersonajesInactivos();
		}
	}

	public void recuperarMagia() {
		for (Personaje personaje : personajes) {
			personaje.incrementarNivelMagia(rand.nextInt(0, 20));
		}
	}

	private Personaje obtenerPersonajeSaludable() {
		List<Personaje> personajesSaludables = personajes.stream()
				.filter(personaje -> personaje.estaSaludable() && !personaje.getTurnoPerdido())
				.collect(Collectors.toList());
		return personajesSaludables.isEmpty() ? null : personajesSaludables.get(rand.nextInt(personajesSaludables.size()));
	}
 
	private List<String> consultarHechizosDisponibles(Personaje atacante) { // Integracion con prolog
		try {
			//Nos conectamos con prolog
			Query queryConnection = new Query("consult", new Term[] { new Atom("MagosVsMortifagosV2.pl") });
			queryConnection.hasSolution(); 

			//Formateo de set hechizosLanzadosEquipoRonda para la query de prolog
			String listaHechizosLanzados = hechizosLanzadosEquipoRonda.stream()
				    .map(hechizo -> hechizo.obtenerNombre().toLowerCase())
				    .collect(Collectors.joining(", ", "[", "]"));
			
			//Formateo de query para prolog
			String queryStr = String.format("hechizos_disponibles(%d, %s, %s, Hechizos)",
					atacante.getNivelMagia(),
					atacante.getTipo().toString().toLowerCase(),
					listaHechizosLanzados);

			//Retornamos lista de hechizos disponibles
			Query queryHechizosDisponibles = new Query(queryStr);
			if (queryHechizosDisponibles.hasSolution()) {
				Term hechizosTerm = queryHechizosDisponibles.oneSolution().get("Hechizos");
				return Arrays.stream(Term.listToTermArray(hechizosTerm))
						.map(Term::name)
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			System.err.println("Error al consultar hechizos disponibles: " + e.getMessage());
		}
		return Collections.emptyList();
	}

	private Hechizo seleccionarHechizoDisponible(List<String> hechizosDisponibles) {
		String hechizoNombre = hechizosDisponibles.get(rand.nextInt(hechizosDisponibles.size()));
		return HechizoFactory.crearHechizo(hechizoNombre);
	}
	
	private void ejecutarHechizo(Personaje atacante, Personaje objetivo, Hechizo hechizo) {
		hechizo.ejecutar(atacante, objetivo);
		hechizosLanzadosEquipo.add(hechizo);
		agregarHechizoLanzado(atacante, hechizo);
		hechizosLanzadosEquipoRonda.add(hechizo);
	}

	private void lanzarPocion(Personaje atacante) {
		Pocion pocion = PocionFactory.crearPocion();
		atacante.actualizarInventarioPociones(-1);
		pocion.aplicarEfecto(atacante);	
	}

	public void mostrarHechizosLanzadosPorPersonaje() {
		hechizosLanzadosPorPersonaje.forEach((personaje, hechizos) -> {
			System.out.println("Personaje: " + personaje.getNombre() + ", Hechizos lanzados: ");
			hechizos.forEach(hechizo -> System.out.println(hechizo.obtenerNombre()));
		});
	}

	private void agregarHechizoLanzado(Personaje personaje, Hechizo hechizo) {
		hechizosLanzadosPorPersonaje
				.computeIfAbsent(personaje, k -> new ArrayList<>())
				.add(hechizo);
	}

	public void eliminarPersonajesInactivos() {
		personajes.removeIf(personaje -> !personaje.estaSaludable());
	}
}
