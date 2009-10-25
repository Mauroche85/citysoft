package ar.edu.utn.frba.proyecto.citysoft.modelo;

/**
 * @author Hernan
 * @version 1.0
 * @created 10-Oct-2009 2:28:49 p.m.
 */

public class Conductor extends Persona implements ObjetoDeDominio, Comparable<Conductor> {

	private String nombreUsuario;
	private int idConductor;
	private Vehiculo vehiculo;

	// **************************************
	// ** Constructor
	// **************************************

	public Conductor() {
		this.idConductor = Central.getInstance().getGeneradorDeIds().getProximoIdConductor();
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo v) {
		this.vehiculo = v;
	}

	public int compareTo(Conductor theOther) {
		int thisId = this.idConductor;
		int theOtherId = theOther.idConductor;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

}