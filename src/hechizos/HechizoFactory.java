package hechizos;

import java.util.Random;

import personajes.TipoPersonaje;

public class HechizoFactory {
	public static Hechizo crearHechizo(TipoPersonaje tipoPersonaje) {
        Random rand = new Random();
        switch(tipoPersonaje) {
        case MAGO:
            Hechizo[] hechizosMagos = {
        		new Protego(),
        		new Expelliarmus(),
        		new Stupefy(),
        		new PetrificusTotalus(),
        		new ExpectoPatronum()
            };
            return hechizosMagos[rand.nextInt(hechizosMagos.length)]; //Entiendo que habria que hacerlo hacia  (hechizosMagos.length - 1) porque el length seria 5? 
        case MORTIFAGO:
            Hechizo[] hechizosMortifagos = {
	    		new Protego(),
	    		new Imperius(),
	    		new Crucio(),
	    		new AvadaKedavra(),
	    		new Sectumsempra()
            };
            return hechizosMortifagos[rand.nextInt(hechizosMortifagos.length)]; //Entiendo que habria que hacerlo hacia  (hechizosMagos.length - 1) porque el length seria 5?
        default:
            throw new IllegalArgumentException("Tipo de personaje desconocido");
        }
    }
}
