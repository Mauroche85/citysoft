package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

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
	// ** Attributes
	// **************************************

	private Collection<Taxi> taxis = new ArrayList<Taxi>();
	private Collection<Conductor> conductores = new ArrayList<Conductor>();
	private Collection<Cliente> clientes = new ArrayList<Cliente>();
	private Collection<Viaje> viajes = new ArrayList<Viaje>();

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
