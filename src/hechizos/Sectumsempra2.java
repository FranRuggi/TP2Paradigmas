package hechizos;


import personajes.Personaje;

public class Sectumsempra2 implements Hechizo {
	private static final int COSTO = 60;

	@Override
	public boolean ejecutar(Personaje lanzador, Personaje objetivo) {
    	
			if (lanzador.getNivelMagia() < COSTO)
				return false;
			lanzador.disminuirNivelMagia(COSTO);
			objetivo.recibirDaño(50); // Aplica un daño oscuro y crítico
			return true;
    	
	}
	
	
	@Override
	public String obtenerNombre() {
		return "Sectumsempra";
	}
}