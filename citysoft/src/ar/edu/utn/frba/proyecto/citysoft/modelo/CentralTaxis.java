package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ar.edu.utn.frba.proyecto.citysoft.modelo.persistencia.ColeccionPersistente;

public class CentralTaxis implements ObjetoDeDominio {

	// **************************************
	// ** Constructor(s)
	// **************************************

	private CentralTaxis() {
	}

	private static CentralTaxis thiz = new CentralTaxis();

	public static CentralTaxis getInstance() {
		return thiz;
	}

	// **************************************
	// ** Initialization
	// **************************************

	/**
	 * Para que carge de la DB todos los datos
	 */
	public void initialize() {
		this.taxis = new ColeccionPersistente<Taxi>(Taxi.class);
		this.conductores = new ColeccionPersistente<Conductor>(Conductor.class);
		this.clientes = new ColeccionPersistente<Cliente>(Cliente.class);
		this.viajes = new ColeccionPersistente<Viaje>(Viaje.class);
	}

	public void terminate() {
		thiz = null;
	}

	// **************************************
	// ** Attributes
	// **************************************

	private Collection<Taxi> taxis;
	private Collection<Conductor> conductores;
	private Collection<Cliente> clientes;
	private Collection<Viaje> viajes;

	// **************************************
	// ** Accessors
	// **************************************

	public Collection<Taxi> getTaxis() {
		return taxis;
	}

	public void addTaxi(Taxi taxi) {
		this.taxis.add(taxi);
	}

	public Collection<Conductor> getConductores() {
		return conductores;
	}

	public void addConductor(Conductor conductor) {
		this.conductores.add(conductor);
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void addViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}

	// **************************************
	// ** Finders
	// **************************************

	public Cliente getCliente(final String usuario) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Cliente) CollectionUtils.find(this.clientes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Cliente c = (Cliente) arg0;
				return c.getNombreUsuario().equals(usuario);
			}
		});
	}

	public Taxi getTaxiPorTrackerId(final String idTracker) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Taxi) CollectionUtils.find(this.taxis, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Taxi t = (Taxi) arg0;
				return t.getIdTracker().equals(idTracker);
			}
		});
	}

	public Taxi getTaxi(final int idTaxi) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Taxi) CollectionUtils.find(this.taxis, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Taxi t = (Taxi) arg0;
				return t.getIdTaxi() == idTaxi;
			}
		});
	}

	public Viaje getViaje(final int idViaje) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Viaje) CollectionUtils.find(this.viajes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Viaje v = (Viaje) arg0;
				return v.getIdViaje() == idViaje;
			}
		});
	}

}
