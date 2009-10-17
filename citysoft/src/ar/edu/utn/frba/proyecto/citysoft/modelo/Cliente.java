package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Alejandro
 * @version 1.0
 * @created 23-Jul-2009 10:54:49 p.m.
 */
public class Cliente extends Persona implements ObjetoDeDominio, Comparable<Cliente> {

	private String nombreUsuario;
	private int idCliente;
	private Collection<Viaje> viajes = new ArrayList<Viaje>();
	private Viaje viajeEnCurso;

	// **************************************
	// ** Constructor
	// **************************************

	public Cliente() {
		this.idCliente = CentralTaxis.getInstance().getGeneradorDeIds().getProximoIdCliente();
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje getViajeEnCurso() {
		return viajeEnCurso;
	}

	public void setViajeEnCurso(Viaje viajeEnCurso) {
		this.viajeEnCurso = viajeEnCurso;
	}

	public int compareTo(Cliente theOther) {
		int thisId = this.idCliente;
		int theOtherId = theOther.idCliente;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

}