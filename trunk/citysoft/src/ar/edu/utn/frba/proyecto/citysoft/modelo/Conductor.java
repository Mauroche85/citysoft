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

	/**
	 * Hay doble conocimiento. El vehiculo sabe de su conductor, asi como que el
	 * conductor sabe de su vehiculo. Por eso, se puede realizar en enlace de
	 * cualquiera de los 2 lados, pues ambos le informaran al otro de la nueva
	 * relacion
	 */
	public void setVehiculo(Vehiculo v) {
		this.vehiculo = v;
		if (v.getConductor() != null && !v.getConductor().equals(this))
			v.setConductor(this);
	}

	public int compareTo(Conductor theOther) {
		int thisId = this.idConductor;
		int theOtherId = theOther.idConductor;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

}