package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;

/**
 * @author Alejandro
 * @version 1.0
 * @created 23-Jul-2009 10:54:52 p.m.
 */
public class Track implements ObjetoDeDominio, Comparable<Track> {

	private int idTrack;
	private Vehiculo vehiculo;
	private Date instante;
	private Coordenadas coordenadas;

	// **************************************
	// ** Accessors
	// **************************************

	public int getIdTrack() {
		return idTrack;
	}

	public void setIdTrack(int idTrack) {
		this.idTrack = idTrack;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo v) {
		this.vehiculo = v;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	// **************************************
	// ** IMPLEMENTACIÓN: Comparable
	// **************************************

	@Override
	public int compareTo(Track o) {
		return this.getInstante().compareTo(o.getInstante());
	}

}