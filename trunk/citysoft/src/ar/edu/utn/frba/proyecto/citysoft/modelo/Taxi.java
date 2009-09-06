package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @version 1.0
 * @param <T>
 * @created 23-Jul-2009 10:54:52 p.m.
 */
public class Taxi implements ObjetoDeDominio, Comparable<Taxi> {

	private int idTaxi;
	private String idTracker;
	private String marca;
	private String modelo;
	private String patente;
	private String detalle;
	private Conductor conductor;
	private Collection<Viaje> viajes = new ArrayList<Viaje>();
	private SortedSet<Track> tracks = new TreeSet<Track>();
	private Viaje viajeEnCurso;

	// **************************************
	// ** Accessors
	// **************************************

	public int getIdTaxi() {
		return idTaxi;
	}

	public void setIdTaxi(int idTaxi) {
		this.idTaxi = idTaxi;
	}

	public String getIdTracker() {
		return idTracker;
	}

	public void setIdTracker(String idTracker) {
		this.idTracker = idTracker;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void addViaje(Viaje viaje) {
		viaje.setTaxi(this);
		this.viajes.add(viaje);
	}

	public SortedSet<Track> getTracks() {
		return tracks;
	}

	public void addTrack(Track track) {
		this.tracks.add(track);
	}

	public Viaje getViajeEnCurso() {
		return viajeEnCurso;
	}

	public void setViajeEnCurso(Viaje viajeEnCurso) {
		this.viajeEnCurso = viajeEnCurso;
	}

	// **************************************
	// ** Execution
	// **************************************

	public Track getUltimoTrack() {
		return this.getTracks().last();
	}

	public void nuevoTrack(double lat, double lng) {
		Track t = new Track();
		// TODO ver que onda con el ID de track
		t.setIdTrack(-1);
		t.setInstante(new Date());
		t.setTaxi(this);
		t.setCoordenadas(new Coordenadas(lat, lng));
		this.tracks.add(t);
	}

	@Override
	public int compareTo(Taxi theOtherTaxi) {
		int thisId = this.idTaxi;
		int theOtherId = theOtherTaxi.idTaxi;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

}