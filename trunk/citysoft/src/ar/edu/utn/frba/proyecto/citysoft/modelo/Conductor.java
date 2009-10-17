package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Hernan
 * @version 1.0
 * @created 10-Oct-2009 2:28:49 p.m.
 */

public class Conductor extends Persona implements ObjetoDeDominio, Comparable<Conductor> {

	private String nombreUsuario;
	private int idConductor;
	private Taxi taxi;

	// **************************************
	// ** Constructor
	// **************************************

	public Conductor() {
		this.idConductor = CentralTaxis.getInstance().getGeneradorDeIds().getProximoIdConductor();
	}

	// **************************************
	// ** Accessors
	// **************************************

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombre) {
		this.nombreUsuario = nombre;
	}

	public int getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(int idConductor) {
		this.idConductor = idConductor;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public int compareTo(Conductor theOther) {
		int thisId = this.idConductor;
		int theOtherId = theOther.idConductor;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

}