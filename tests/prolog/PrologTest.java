package prolog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.junit.jupiter.api.Test;

class PrologTest {

	@Test
	void testConexionConProlog() {
		Query queryConnection = new Query("consult", new Term[] { new Atom("MagosVsMortifagosV2.pl") });
		
		assertTrue(queryConnection.hasSolution());
	}
	
	@Test
	void testNoQuedanHechizosParaLanzarMagoConMagia() {
		Query queryConnection = new Query("consult", new Term[] { new Atom("MagosVsMortifagosV2.pl") });
		
		queryConnection.hasSolution();
		
		String listaHechizosLanzados = "[protego, expelliarmus, stupefy, petrificusTotalus, expectoPatronum]";
		String queryStr = "hechizos_disponibles(200, mago," + listaHechizosLanzados + ", Hechizos)";
		Query queryHechizosDisponibles = new Query(queryStr);
		
		// Si la consulta tiene solución, obtenemos el término para la variable "Hechizos"
	    assertTrue(queryHechizosDisponibles.hasSolution());
        Term hechizosTerm = queryHechizosDisponibles.oneSolution().get("Hechizos");

        // Convertimos el término a una lista y verificamos que esté vacía
        List<String> hechizosObtenidos = Arrays.stream(Term.listToTermArray(hechizosTerm))
                                               .map(Term::name)
                                               .collect(Collectors.toList());

        // Verificamos que la lista de hechizos esté vacía
        assertTrue(hechizosObtenidos.isEmpty(), "La lista de hechizos debería estar vacía");
	}
	
	@Test
	void testMagiaInsuficienteParaCualquierHechizo() {
		Query queryConnection = new Query("consult", new Term[] { new Atom("MagosVsMortifagosV2.pl") });
		
		queryConnection.hasSolution();
		
		String listaHechizosLanzados = "[]";
		String queryStr = "hechizos_disponibles(0, mago," + listaHechizosLanzados + ", Hechizos)";
		Query queryHechizosDisponibles = new Query(queryStr);
		
	    // Si la consulta tiene solución, obtenemos el término para la variable "Hechizos"
	    assertTrue(queryHechizosDisponibles.hasSolution());
        Term hechizosTerm = queryHechizosDisponibles.oneSolution().get("Hechizos");

        // Convertimos el término a una lista y verificamos que esté vacía
        List<String> hechizosObtenidos = Arrays.stream(Term.listToTermArray(hechizosTerm))
                                               .map(Term::name)
                                               .collect(Collectors.toList());

        // Verificamos que la lista de hechizos esté vacía
        assertTrue(hechizosObtenidos.isEmpty(), "La lista de hechizos debería estar vacía");
	}
	
	@Test
	void testMagiaSuficienteParaCualquierHechizo() {
		Query queryConnection = new Query("consult", new Term[] { new Atom("MagosVsMortifagosV2.pl") });
		
		queryConnection.hasSolution();
		
		String listaHechizosLanzados = "[]";
		String queryStr = "hechizos_disponibles(200, mago," + listaHechizosLanzados + ", Hechizos)";
		Query queryHechizosDisponibles = new Query(queryStr);
		
		queryHechizosDisponibles.hasSolution();
		
		// Convertimos el término a una lista de hechizos
		Term hechizosTerm = queryHechizosDisponibles.oneSolution().get("Hechizos");
		 List<String> hechizosObtenidos = Arrays.stream(Term.listToTermArray(hechizosTerm))
                 .map(Term::name)
                 .collect(Collectors.toList());

		// Creamos la lista esperada
		List<String> hechizosEsperados = Arrays.asList("stupefy", "protego", "petrificusTotalus", "expelliarmus", "expectoPatronum");
		
		// Comparamos las listas
		assertEquals(hechizosEsperados, hechizosObtenidos);
	}

}
