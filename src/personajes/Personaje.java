package personajes;

import java.util.ArrayList;
import java.util.List;

import hechizos.Hechizo;

public abstract class Personaje {
	protected String nombre;
	protected int nivelMagia;
	protected int puntosVida;
	protected boolean turnoPerdido = false;
	protected List<Hechizo> hechizos;

	public Personaje(String nombre, int nivelMagia, int puntosVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosVida = puntosVida;
		this.hechizos = new ArrayList<Hechizo>();
	}

	public abstract boolean lanzarHechizo(Personaje objetivo, Hechizo hechizo);

	public abstract TipoPersonaje getTipo();

	public void desarmar() {
		turnoPerdido = true;
	}

	public void petrificar() {
		turnoPerdido = true;
	}

	public void aturdir() {
		turnoPerdido = true;
	}

	// Método que se llama al iniciar cada turno para verificar si el personaje
	// puede actuar
	public boolean puedeActuar() {
		if (turnoPerdido) {
			turnoPerdido = false; // Resetea el turno perdido para el siguiente turno
			return false;
		}
		return true;
	}

	// Método para resetear efectos de control, aturdimiento y petrificación al
	// finalizar el turno
	public void finDeTurno() {
		turnoPerdido = false;
	}

	public void agregarHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}

	public void recibirDaño(int danio) {
		puntosVida -= danio;
		if (puntosVida < 0) {
			puntosVida = 0;
		}
	}

	public void disminuirNivelMagia(int cantidad) {
		if (cantidad > nivelMagia)
			setNivelMagia(0);
		else
			setNivelMagia(nivelMagia - cantidad);
		System.out.println(
				nombre + " ha usado " + cantidad + " puntos de magia. Puntos de magia restantes: " + nivelMagia);
	}

	public void incrementarNivelMagia(int cantidad) {
		setNivelMagia(nivelMagia + cantidad);
	}

	private void setNivelMagia(int nivelMagia) {
		this.nivelMagia = nivelMagia;
	}

	public boolean estaSaludable() {
		return puntosVida > 0;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public int getNivelMagia() {
		return nivelMagia;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public boolean getTurnoPerdido() {
		return turnoPerdido;
	}
}
