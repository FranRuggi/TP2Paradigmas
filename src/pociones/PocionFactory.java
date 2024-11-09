package pociones;

import java.util.Random;

public class PocionFactory {
    public static Pociones crearPocion() {
    	Random rand = new Random();
    	Pociones[] PocionesDisponibles = {
    			new Curacion(),
    			new NivelMagia()
    	};
    	
    	return PocionesDisponibles[rand.nextInt(PocionesDisponibles.length)];  
    }
}