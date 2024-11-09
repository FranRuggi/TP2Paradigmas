package hechizos;

public class HechizoFactory {

	public static Hechizo crearHechizo(String nombreHechizo) {

		switch (nombreHechizo.toLowerCase()) {
		case "protego":
			return new Protego();
		case "expelliarmus":
			return new Expelliarmus();
		case "stupefy":
			return new Stupefy();
		case "petrificustotalus":
			return new PetrificusTotalus();
		case "expectopatronum":
			return new ExpectoPatronum();
		case "imperius":
			return new Imperius();
		case "crucio":
			return new Crucio();
		case "avadakedavra":
			return new AvadaKedavra();
		case "sectumsempra":
			return new Sectumsempra();
		default:
			throw new IllegalArgumentException("Nombre de hechizo desconocido: " + nombreHechizo);

		}
	}
}
