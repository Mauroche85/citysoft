package ar.edu.utn.frba.proyecto.citysoft.modelo;

/**
 * @version 1.0
 * @created 23-Jul-2009 10:54:50 p.m.
 */
public class Conductor extends Persona implements ObjetoDeDominio {

	private int idConductor;
	private Taxi taxi;

	// **************************************
	// ** Accessors
	// **************************************

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

}