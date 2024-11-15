package main;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.Batallon;
import personajes.PersonajeFactory;


public class BatallaMagosVsMortifagosTest {
	 
	Batallon batallonMagos;
    Batallon batallonMortifagos;
    PersonajeFactory fabricaDePersonajes;
    
    @BeforeEach
    void setUp() {
    	batallonMagos = new Batallon();
        batallonMortifagos = new Batallon();
        fabricaDePersonajes = new PersonajeFactory();
    }
    
    
    @Test
    public void testGananMagos() {
        // Simulamos la batalla hasta que uno de los 2 batallones se queda sin personajes saludables
        //Como el juego es aleatorio, para asegurarnos que los magos ganen, generaremos mas magos que mortifagos.
        for (int i = 0; i < 3; i++) 
            batallonMagos.agregarPersonaje(fabricaDePersonajes.crearMago());

        //Creamos 3 magos y 1 solo mortifago
        batallonMortifagos.agregarPersonaje(fabricaDePersonajes.crearMortifago());
        
        
        int ronda = 0;
        while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
            batallonMagos.recuperarMagia();
            batallonMortifagos.recuperarMagia();

            if (ronda % 2 == 0) {
                batallonMagos.atacar(batallonMortifagos);
                if (batallonMortifagos.tienePersonajesSaludables()) {
                    batallonMortifagos.atacar(batallonMagos);
                }
            } else {
                batallonMortifagos.atacar(batallonMagos);
                if (batallonMagos.tienePersonajesSaludables()) {
                    batallonMagos.atacar(batallonMortifagos);
                }
            }
            ronda++;
        }

        // verificamos si los magos son los que ganan
        assertTrue(batallonMagos.tienePersonajesSaludables(), "Los Magos deberían ganar la batalla");
    }
    @Test
    public void testGananMortifagos() {
    	// Simulamos la batalla hasta que uno de los 2 batallones se queda sin personajes saludables
    	//Como el juego es aleatorio, para asegurarnos que los mortifagos ganen, generaremos mas mortifagos que magos.
    	for (int i = 0; i < 3; i++) 
    		batallonMortifagos.agregarPersonaje(fabricaDePersonajes.crearMago());
    	
    	//Creamos 3 mortifago y 1 solo Mago
    	batallonMagos.agregarPersonaje(fabricaDePersonajes.crearMortifago());
    	
    	
    	int ronda = 0;
    	while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
    		batallonMagos.recuperarMagia();
    		batallonMortifagos.recuperarMagia();
    		
    		if (ronda % 2 == 0) {
    			batallonMagos.atacar(batallonMortifagos);
    			if (batallonMortifagos.tienePersonajesSaludables()) {
    				batallonMortifagos.atacar(batallonMagos);
    			}
    		} else {
    			batallonMortifagos.atacar(batallonMagos);
    			if (batallonMagos.tienePersonajesSaludables()) {
    				batallonMagos.atacar(batallonMortifagos);
    			}
    		}
    		ronda++;
    	}
    	
    	// verificamos si los magos son los que ganan
    	assertTrue(batallonMortifagos.tienePersonajesSaludables(), "Los Mortifagos deberían ganar la batalla");
    }
}












