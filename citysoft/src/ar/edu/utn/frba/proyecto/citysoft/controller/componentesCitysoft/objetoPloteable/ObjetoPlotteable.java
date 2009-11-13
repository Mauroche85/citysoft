package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.List;

import org.zkoss.gmaps.Gpolyline;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityPolilinea;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;

/**
 * Representa a un determinado objeto del modelo que tenga uno o varios aspectos
 * representables en un {@link CityMapa}. Ejemplos:
 * 
 * <li>El vehiculo (objeto del modelo) tiene una posicion en un mapa (aspecto
 * representable)
 * 
 * <li>Una solicitud de viaje (objeto del modelo) tiene un origen y un destino
 * (ambos aspectos representables)
 * 
 * <li>Un equipo de futbol (objeto del model) tiene jugadores repartidos dentro
 * de un campo de juego (12 aspectos representables).
 * 
 * <br>
 * Para dejar las cosas bien claras, si hablamos de un equipo de futbol,
 * entonces este objeto representa al equipo en si mismo, y no a sus jugadores.
 * Así, este objeto debe ofrecerme la posibilidad de mostrarme la posicion de
 * todos sus jugadores. Consecuentemente, este objto debe tener la capacidad de
 * hablar con el equipo para pedirle todos sus jugadores y luego preguntarle las
 * coordenadas a cada uno de ellos.
 */
public abstract class ObjetoPlotteable implements Comparable<ObjetoPlotteable> {

	public abstract ObjetoDeDominio getObjetoDelModelo();

	/**
	 * Crea y/o attachean los marcadores y otros elementos particulares de cada
	 * subclase al mapa recibido por parametro. Si los marcadores y otros
	 * elementos ya existian y pertenecian a otro mapa, entonces deberan
	 * desatacharse del mapa corriente y se atacharan al mapa nuevo.
	 * 
	 * Tengo marcadores y otros elementos <==> tengo mapa
	 */
	public void mostrarEnMapa(CityMapa nuevoMapa) {
		this.estoyPloteado = true;
		// Si tengo un mapa viejo, remuevo los elementos ploteables del mismo
		if (estoyPloteado()) {
			for (CityMarcador m : getMarcadores())
				m.detach();
			for (Gpolyline p : getPolilineas())
				p.detach();
		}
		// Los elementos quedaron flotando, entonces los pongo en el nuevo mapa
		for (CityMarcador m : getMarcadores())
			m.setParent(nuevoMapa);
		for (Gpolyline p : getPolilineas())
			p.setParent(nuevoMapa);
		// Ahora si... declaramos el nuevo mapa!
		this.mapa = nuevoMapa;
	}

	/**
	 * ELIMINA los marcadores y otros elementos del mapa. Debe hacer un detach y
	 * luego tirarlos a la basura porque el detach parece indicar que si no lo
	 * reattachas, la arquitectura disposea los recursos asociados en el
	 * cliente. Para ello, el marcador u objeto ploteable, tendrá una marca
	 * isDirty que si es true, entonces el getMarcadores debe eliminarlo y
	 * generar otro nuevo.
	 * 
	 * Tengo marcadores y otros elementos <==> tengo mapa
	 */
	public void quitarDelMapa() {
		this.estoyPloteado = false;
		// Nos descentramos
		if (soyCentrable() && soyCentro())
			descentrar();
		// Eliminamos los marcadores y otros elementos ploteables
		for (CityMarcador m : getMarcadores()) {
			m.detach();
		}
		for (Gpolyline p : getPolilineas())
			p.detach();
		// Eliminamos la refrencia al mapa
		this.mapa = null;
	}

	// **************************************
	// ** CENTRO
	// **************************************

	public final boolean soyCentrable() {
		return getMarcadorCentral() != null;
	}

	public final boolean soyCentro() {
		return getMarcadorCentral().equals(this.mapa.quienMeCentra());
	}

	public final void centrar() {
		this.mapa.tendrasComoCentroA(getMarcadorCentral());
	}

	public final void descentrar() {
		this.mapa.descentrar();
	}

	/**
	 * @return el marcador que actuaría como centro del mapa, si marcamos a este
	 *         objeto plotteable como centro. O null, para definir que no somos
	 *         centrables.
	 */
	public CityMarcador getMarcadorCentral() {
		return null;
	}

	// **************************************
	// ** MARCADORES Y POLILINEAS
	// **************************************

	/**
	 * Devuelve los marcadores correspondiente. Es lazy, por lo cual genera los
	 * elementos si no los tiene al momento de ser invocado este método.
	 * OJO!!!!! fijarse que si elementoPloteable.isDirty, no sirve y hay que
	 * volver a generarlo.
	 */
	public abstract List<CityMarcador> getMarcadores();

	public abstract List<CityPolilinea> getPolilineas();

	public boolean estoyPloteado() {
		return this.estoyPloteado;
	}

	/**
	 * Toma todos sus marcadores y otros elementos ploteables, y los actualiza.
	 * Ej: si este objeto referencia a un viaje en curso, toma el marcador
	 * correspondiente al vehiculo y le actualiza las coordenadas
	 */
	public abstract void actualizarPlotting();

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	protected CityMapa mapa;
	protected boolean estoyPloteado;

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public int compareTo(ObjetoPlotteable o) {
		return this.getObjetoDelModelo().compareTo(o.getObjetoDelModelo());
	}

}
