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
            return hechizosMagos[rand.nextInt(hechizosMagos.length)];
        case MORTIFAGO:
            Hechizo[] hechizosMortifagos = {
	    		new Protego(),
	    		new Imperius(),
	    		new Crucio(),
	    		new AvadaKedavra(),
	    		new Sectumsempra()
            };
            return hechizosMortifagos[rand.nextInt(hechizosMortifagos.length)];
        default:
            throw new IllegalArgumentException("Tipo de personaje desconocido");
        }
    }
}
