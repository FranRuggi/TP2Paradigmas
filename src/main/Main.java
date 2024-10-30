package main;



import org.jpl7.*;
import org.jpl7.Integer;



public class Main {
    public static void main(String[] args) {
        // Intenta cargar el archivo Prolog
    	Query q1 = 
    		    new Query( 
    			"consult", 
    			new Term[] {new Atom("MagosVsMortifagos.pl")} 
    		    );
    	System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
    	Query q2 = new Query("ejecutar", new Term[] {new Integer(20), new Integer(10)});
    	System.out.println((q2.hasSolution() ? "succeeded" : "failed"));
    	
}
}
