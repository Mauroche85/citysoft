package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ar.edu.utn.frba.proyecto.citysoft.modelo.persistencia.ColeccionPersistente;


public class Central implements ObjetoDeDominio {

	// **************************************
	// ** Constructor(s)
	// **************************************

	private Central() {
	}

	private static Central thiz = new Central();

	public static Central getInstance() {
		return thiz;
	}

	// **************************************
	// ** Initialization
	// **************************************

	/**
	 * Para que carge de la DB todos los datos
	 */
	public void initialize() {
		this.setGeneradorDeIds(GeneradorDeIds.getInstance());
		this.vehiculos = new ColeccionPersistente<Vehiculo>(Vehiculo.class);
		this.conductores = new ColeccionPersistente<Conductor>(Conductor.class);
		this.clientes = new ColeccionPersistente<Cliente>(Cliente.class);
		this.viajes = new ColeccionPersistente<Viaje>(Viaje.class);
	}

	public void terminate() {
		thiz = new Central();
	}

	// **************************************
	// ** Attributes
	// **************************************

	private GeneradorDeIds generadorDeIds;
	private Collection<Vehiculo> vehiculos;
	private Collection<Conductor> conductores;
	private Collection<Cliente> clientes;
	private Collection<Viaje> viajes;

	// **************************************
	// ** Accessors
	// **************************************

	public Collection<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	@SuppressWarnings("unchecked")
	public Collection<Vehiculo> getVehiculosDesactivados() {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Collection<Vehiculo>) CollectionUtils.select(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo t = (Vehiculo) arg0;
				return !t.getActivado();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Collection<Vehiculo> getVehiculosLibres() {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Collection<Vehiculo>) CollectionUtils.select(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo t = (Vehiculo) arg0;
				return t.getActivado() && t.estoyLibre();
			}
		});
	}

		
	public void addVehiculo(Vehiculo v) {
		this.vehiculos.add(v);
	}

	public void updateVehiculo(Vehiculo v) {
		this.vehiculos.add(v);
	}

	public Collection<Conductor> getConductores() {
		return conductores;
	}

	public void addConductor(Conductor conductor) {		
		 this.conductores.add(conductor);
	}
	
	public void updateConductor(Conductor conductor) {
		this.conductores.add(conductor);
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public void updateCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	@SuppressWarnings("unchecked")
	public Collection<Viaje> getViajesBajoTransporte() {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Collection<Viaje>) CollectionUtils.select(this.viajes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Viaje v = (Viaje) arg0;
				return v.estaTransportando();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Collection<Viaje> getViajesAsignados() {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Collection<Viaje>) CollectionUtils.select(this.viajes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Viaje v = (Viaje) arg0;
				return v.estaAsignado();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Collection<Viaje> getViajesPendientes() {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Collection<Viaje>) CollectionUtils.select(this.viajes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Viaje v = (Viaje) arg0;
				return v.estaPendiente();
			}
		});
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

	public Cliente getClientePorId(final int idCliente) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Cliente) CollectionUtils.find(this.clientes, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Cliente c = (Cliente) arg0;
				return c.getIdCliente() == idCliente;
			}
		});
	}

	public Conductor getConductor(final String usuario) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Conductor) CollectionUtils.find(this.conductores, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Conductor c = (Conductor) arg0;
				return c.getNombreUsuario().equals(usuario);
			}
		});
	}

	public Conductor getConductorPorId(final int idConductor) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Conductor) CollectionUtils.find(this.conductores, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Conductor c = (Conductor) arg0;
				return c.getIdConductor() == idConductor;
			}
		});
	}

	public Vehiculo getVehiculoPorTrackerId(final String idTracker) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Vehiculo) CollectionUtils.find(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo t = (Vehiculo) arg0;
				return t.getIdTracker().equals(idTracker);
			}
		});
	}

	public Vehiculo getVehiculo(final String usuario) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Vehiculo) CollectionUtils.find(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo c = (Vehiculo) arg0;
				return c.getNombreUsuario().equals(usuario);
			}
		});
	}

	public Vehiculo getVehiculoPorId(final int idVehiculo) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Vehiculo) CollectionUtils.find(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo c = (Vehiculo) arg0;
				return c.getIdVehiculo() == idVehiculo;
			}
		});
	}

	public Vehiculo getVehiculoPorPatente(final String patente) {
		// TODO: que pasa si no lo encuentra??? devuelve null???
		return (Vehiculo) CollectionUtils.find(this.vehiculos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Vehiculo c = (Vehiculo) arg0;
				return c.getPatente() == patente;
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

	public void setGeneradorDeIds(GeneradorDeIds generadorDeIds) {
		this.generadorDeIds = generadorDeIds;
	}

	public GeneradorDeIds getGeneradorDeIds() {
		return generadorDeIds;
	}

}
