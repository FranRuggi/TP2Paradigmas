package personajes;

import java.util.ArrayList;
import java.util.List;

import hechizos.Hechizo;
import pociones.Pocion;

public abstract class Personaje {
	protected String nombre;
	protected int nivelMagia;
	protected int puntosVida;
	protected boolean turnoPerdido = false;
	protected List<Hechizo> hechizosLanzados;
	protected int inventarioPociones = 3;

	public Personaje(String nombre, int nivelMagia, int puntosVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosVida = puntosVida;
		this.hechizosLanzados = new ArrayList<Hechizo>();
	}

	public abstract boolean lanzarHechizo(Personaje objetivo, Hechizo hechizo);

	public abstract TipoPersonaje getTipo();

	public abstract void lanzarPocion(Personaje objetivo, Pocion pocion);

	public void desarmar() {
		turnoPerdido = true;
	}

	public void petrificar() {
		turnoPerdido = true;
	}

	public void aturdir() {
		turnoPerdido = true;
	}

	public boolean puedeActuar() {
		if (turnoPerdido) {
			turnoPerdido = false; // Resetea el turno perdido para el siguiente turno
			return false;
		}
		return true;
	}

	public void agregarHechizoLanzado(Hechizo hechizo) {
		hechizosLanzados.add(hechizo);
	}

	public void recibirDaño(int danio) {
		puntosVida -= danio;
		if (puntosVida < 0) {
			puntosVida = 0;
		}
	}

	public void disminuirNivelMagia(int cantidad) {
		if (cantidad > nivelMagia)
			this.setNivelMagia(0);
		else
			this.setNivelMagia(nivelMagia - cantidad);
		System.out.println(
				this.nombre + " ha usado " + cantidad + " puntos de magia. Puntos de magia restantes: " + this.nivelMagia);
	}

	public void incrementarPuntosVida(int cantidad) {
		setPuntosVida(this.puntosVida + cantidad);
	}

	public void incrementarNivelMagia(int cantidad) {
		setNivelMagia(this.nivelMagia + cantidad);
	}

	protected void setNivelMagia(int nivelMagia) {
		this.nivelMagia = nivelMagia;
	}

	public boolean estaSaludable() {
		return this.puntosVida > 0;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getPuntosVida() {
		return this.puntosVida;
	}

	public int getNivelMagia() {
		return this.nivelMagia;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public boolean getTurnoPerdido() {
		return this.turnoPerdido;
	}

	public int getInventarioPociones() {
		return this.inventarioPociones;
	}

	public void setInventarioPociones(int inventarioPociones) {
		this.inventarioPociones = inventarioPociones;
	}

	public void actualizarInventarioPociones(int cantidad) {
		this.inventarioPociones = (this.inventarioPociones + cantidad);
	}
}
