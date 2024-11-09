package pociones;

import java.util.Random;

public class PocionFactory {
    public static Pocion crearPocion() {
    	Random rand = new Random();
    	Pocion[] PocionesDisponibles = {
    			new Curacion(),
    			new NivelMagia()
    	};
    	
    	return PocionesDisponibles[rand.nextInt(PocionesDisponibles.length)];  
    }
}