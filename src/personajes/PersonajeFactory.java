package personajes;

import java.util.Random;

import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

public class PersonajeFactory {
    private static final Random rand = new Random();

	public static Personaje crearMago() {
        int tipo = rand.nextInt(3);
        switch (tipo) {
            case 0: return new Auror("Auror", 100, 100);
            case 1: return new Estudiante("Estudiante", 80, 80);
            case 2: return new Profesor("Profesor", 120, 120);
            default: throw new IllegalArgumentException("Tipo de mago desconocido");
        }
    }
    
    public static Personaje crearMortifago() {
        int tipo = rand.nextInt(2);
        switch (tipo) {
            case 0: return new Comandante("Comandante", 150, 150);
            case 1: return new Seguidor("Seguidor", 90, 90);
            default: throw new IllegalArgumentException("Tipo de mortífago desconocido");
        }
    }
}
