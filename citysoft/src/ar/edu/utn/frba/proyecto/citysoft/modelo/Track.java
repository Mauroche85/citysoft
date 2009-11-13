package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;

/**
 * @author Alejandro
 * @version 1.0
 * @created 23-Jul-2009 10:54:52 p.m.
 */
public class Track implements ObjetoDeDominio {

	private int idTrack;
	private Vehiculo vehiculo;
	private Date instante;
	private Coordenadas coordenadas;

	// **************************************
	// ** Constructor
	// **************************************

	public Track() {
		this.idTrack = Central.getInstance().getGeneradorDeIds().getProximoIdTrack();
	}

	public Track(Date origenDelTrack) {
		this.idTrack = Central.getInstance().getGeneradorDeIds().getProximoIdTrack();
		this.instante = origenDelTrack;
	}

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
	public int compareTo(ObjetoDeDominio o) {
		return this.getInstante().compareTo(((Track) o).getInstante());
	}

}