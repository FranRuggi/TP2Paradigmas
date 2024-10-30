package main;
import java.util.Random;

import personajes.Batallon;
import personajes.PersonajeFactory;

public class BatallaMagosVsMortifagos {
	public static void main(String[] args) {
		Batallon batallonMagos = new Batallon();
		Batallon batallonMortifagos = new Batallon();

		// Crear personajes y añadir hechizos
		for (int i = 0; i < 3; i++) {
			batallonMagos.agregarPersonaje(PersonajeFactory.crearMago());
			batallonMortifagos.agregarPersonaje(PersonajeFactory.crearMortifago());
		}

		Random rand = new Random();
		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
			batallonMagos.recuperarMagia();
			batallonMortifagos.recuperarMagia();
		    
			// Alternar ataques entre facciones
			if (rand.nextBoolean()) {
				System.out.println("Turno de los Magos:");
				batallonMagos.atacar(batallonMortifagos);
				if (batallonMortifagos.tienePersonajesSaludables()) {
					System.out.println("Turno de los Mortífagos:");
					batallonMortifagos.atacar(batallonMagos);
				}
			} else {
				System.out.println("Turno de los Mortífagos:");
				batallonMortifagos.atacar(batallonMagos);
				if (batallonMagos.tienePersonajesSaludables()) {
					System.out.println("Turno de los Magos:");
					batallonMagos.atacar(batallonMortifagos);
				}
			}

			System.out.println("----------------------------");
		}

		if (batallonMagos.tienePersonajesSaludables()) {
			System.out.println("¡Los magos han ganado la batalla!");
		} else {
			System.out.println("¡Los mortífagos han ganado la batalla!");
		}
	}
}
