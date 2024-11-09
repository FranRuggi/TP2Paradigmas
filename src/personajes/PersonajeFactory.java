package personajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

public class PersonajeFactory {
	private static final Random rand = new Random();
	private static List<String> nombresMagos;
	private static List<String> nombresMortifagos;

	static {
		try {
			nombresMagos = cargarNombres("Magos.in");
			nombresMortifagos = cargarNombres("Mortifagos.in");
			Collections.shuffle(nombresMagos); // Baraja la lista para generar nombres aleatorios
			Collections.shuffle(nombresMortifagos); // Baraja la lista para generar nombres aleatorios
		} catch (IOException e) {
			e.printStackTrace();
			nombresMagos = new ArrayList<>();
			nombresMortifagos = new ArrayList<>();
		}
	}

	private static List<String> cargarNombres(String nombreArchivo) throws IOException {
		List<String> nombres = new ArrayList<>();
		try (InputStream inputStream = PersonajeFactory.class.getResourceAsStream(nombreArchivo);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			String linea;
			while ((linea = reader.readLine()) != null) {
				nombres.add(linea);
			}
		}
		return nombres;
	}

	public static Personaje crearMago() {
		if (nombresMagos.isEmpty()) {
			throw new IllegalStateException("No hay más nombres disponibles para magos.");
		}

		String nombre = nombresMagos.remove(0); // Obtiene y elimina el primer nombre de la lista de magos
		int tipo = rand.nextInt(3);
		switch (tipo) {
		case 0:
			return new Auror(nombre, 100, 100);
		case 1:
			return new Estudiante(nombre, 80, 80);
		case 2:
			return new Profesor(nombre, 120, 120);
		default:
			throw new IllegalArgumentException("Tipo de mago desconocido");
		}
	}

	public static Personaje crearMortifago() {
		if (nombresMortifagos.isEmpty()) {
			throw new IllegalStateException("No hay más nombres disponibles para mortífagos.");
		}

		String nombre = nombresMortifagos.remove(0); // Obtiene y elimina el primer nombre de la lista de mortífagos
		int tipo = rand.nextInt(2);
		switch (tipo) {
		case 0:
			return new Comandante(nombre, 150, 150);
		case 1:
			return new Seguidor(nombre, 90, 90);
		default:
			throw new IllegalArgumentException("Tipo de mortífago desconocido");
		}
	}
}
