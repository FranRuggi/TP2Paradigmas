package personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
				continue;

			// Seleccionar un personaje enemigo aleatorio
			Personaje objetivo = otroBatallon.obtenerPersonajeSaludable();
			if (objetivo != null) {
				// Seleccionar un hechizo aleatorio
				Hechizo hechizo = HechizoFactory.crearHechizo(atacante.getTipo()); // Asumiendo que hay un método
																					// getTipo en Personaje
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
