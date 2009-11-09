package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class MapaOperador extends Gmaps implements ConstantesInterfazOperador {

	private static final long serialVersionUID = -6646990321890527463L;

	// **************************************
	// ** ATTRIBUTES
	// **************************************

	private Map<Viaje, Gmarker> mapViajesBajoTransporteSeguidos = new HashMap<Viaje, Gmarker>();
	private Map<Viaje, Gmarker> mapViajesAsignadosSeguidos = new HashMap<Viaje, Gmarker>();
	private Map<Viaje, Gmarker> mapViajesPendientesMarcados = new HashMap<Viaje, Gmarker>();
	private Map<Vehiculo, Gmarker> mapVehiculosSeguidos = new HashMap<Vehiculo, Gmarker>();
	private Object mapaCentradoEn;

	// **************************************
	// ** CENTRO DE MAPA
	// **************************************

	public Object getMapaCentradoEn() {
		return this.mapaCentradoEn;
	}

	public void setMapaCentradoEn(Vehiculo vehiculoLibre) {
		this.mapaCentradoEn = vehiculoLibre;
		centrarMapa();
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void setMapaCentradoEn(Viaje viaje) {
		this.mapaCentradoEn = viaje;
		centrarMapa();
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void centrarMapa() {
		if (this.mapaCentradoEn == null)
			return;
		if (this.mapaCentradoEn.getClass().isAssignableFrom(Vehiculo.class)) {
			Vehiculo t = (Vehiculo) this.mapaCentradoEn;
			setLat(t.getLat());
			setLng(t.getLng());
		} else if (this.mapaCentradoEn.getClass().isAssignableFrom(Viaje.class)) {
			Viaje v = (Viaje) this.mapaCentradoEn;
			if (v.estaPendiente()) {
				setLat(v.getOrigenLatitud());
				setLng(v.getOrigenLongitud());
			} else {
				setLat(v.getVehiculo().getLat());
				setLng(v.getVehiculo().getLng());
			}
		} else {
			throw new RuntimeException("Mapa centrado en algo desconocido (" + this.mapaCentradoEn
					+ ")");
		}
	}

	// **************************************
	// ** MARCAR/DESMARCAR EN MAPA
	// **************************************

	public void setViajeBajoTransporteSeguido(Viaje v, boolean seguir) {
		v.validarViajeTransportando();
		if (seguir) {
			Gmarker marker = buildMarker(v);
			marker.setParent(this);
			this.mapViajesBajoTransporteSeguidos.put(v, marker);
			setMapaCentradoEn(v);
		} else {
			if (v.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapViajesBajoTransporteSeguidos.remove(v));
		}
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void setViajeAsignadoSeguido(Viaje v, boolean seguir) {
		v.validarViajeAsignado();
		if (seguir) {
			Gmarker marker = buildMarker(v);
			marker.setParent(this);
			this.mapViajesAsignadosSeguidos.put(v, marker);
			setMapaCentradoEn(v);
		} else {
			if (v.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapViajesAsignadosSeguidos.remove(v));
		}
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void setViajePendienteMarcado(Viaje v, boolean marcar) {
		v.validarViajePendiente();
		if (marcar) {
			Gmarker marker = buildMarker(v);
			marker.setParent(this);
			this.mapViajesPendientesMarcados.put(v, marker);
			setMapaCentradoEn(v);
		} else {
			if (v.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapViajesPendientesMarcados.remove(v));
		}
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void setVehiculoSeguido(Vehiculo t, boolean seguir) {
		t.validarVehiculoLibre();
		if (seguir) {
			Gmarker marker = buildMarker(t);
			marker.setParent(this);
			this.mapVehiculosSeguidos.put(t, marker);
			setMapaCentradoEn(t);
		} else {
			if (t.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapVehiculosSeguidos.remove(t));
		}
		VentanaInterfazOperador.refrescarVentana(this);
	}

	// **************************************
	// ** PREGUNTAR SI ALGO ESTA MARCADO EN EL MAPA
	// **************************************

	/**
	 * Dice si el viaje asignado o en curso en cuestión, está siendo seguido en
	 * el mapa
	 */
	public boolean estaSiendoSeguidoElViajeBajoTransporte(Viaje v) {
		return this.mapViajesBajoTransporteSeguidos.containsKey(v);
	}

	/**
	 * Dice si el viaje asignado o en curso en cuestión, está siendo seguido en
	 * el mapa
	 */
	public boolean estaSiendoSeguidoElViajeAsignado(Viaje v) {
		return this.mapViajesAsignadosSeguidos.containsKey(v);
	}

	/**
	 * Dice si el viaje pendiente está actualmente marcado en el mapa
	 */
	public boolean estaMarcadoElViajePendiente(Viaje v) {
		return this.mapViajesPendientesMarcados.containsKey(v);
	}

	/**
	 * Dice si el vehiculo libre en cuestion está siendo seguido en el mapa
	 */
	public boolean estaSiendoSeguidoElVehiculo(Vehiculo t) {
		return this.mapVehiculosSeguidos.containsKey(t);
	}

	// **************************************
	// ** REFRESCAR!!! (Esta es la funcion mas importante)
	// **************************************

	public void actualizarPosicionMarkers() {
		// Viajes transportando
		for (Viaje v : this.mapViajesBajoTransporteSeguidos.keySet()) {
			Gmarker gm = this.mapViajesBajoTransporteSeguidos.get(v);
			actualizarMarker(gm, v);
		}
		// Viajes asignados
		for (Viaje v : this.mapViajesAsignadosSeguidos.keySet()) {
			Gmarker gm = this.mapViajesAsignadosSeguidos.get(v);
			actualizarMarker(gm, v);
		}
		// Vehiculos libres
		for (Vehiculo t : this.mapVehiculosSeguidos.keySet()) {
			Gmarker gm = this.mapVehiculosSeguidos.get(t);
			actualizarMarker(gm, t);
		}
		// Viajes pendientes
		for (Viaje v : this.mapViajesPendientesMarcados.keySet()) {
			Gmarker gm = this.mapViajesPendientesMarcados.get(v);
			actualizarMarker(gm, v);
		}
		// Despues de actualizar todos los markers, recentramos el mapa
		centrarMapa();
	}

	private void actualizarMarker(Gmarker gm, Viaje v) {
		if (v.estaPendiente()) {
			gm.setLat(v.getOrigenLatitud());
			gm.setLng(v.getOrigenLongitud());
		} else {
			gm.setLat(v.getVehiculo().getLat());
			gm.setLng(v.getVehiculo().getLng());
		}
	}

	private void actualizarMarker(Gmarker gm, Vehiculo v) {
		gm.setLat(v.getLat());
		gm.setLng(v.getLng());
	}

	// **************************************
	// ** Helpers
	// **************************************

	/**
	 * Crea un gmarker desde un viaje pendiente o en curso (asignado o en
	 * transcurso). <li>Si el viaje esta pendiente, pongo casita fija <li>Si el
	 * viaje esta en curso, pongo pinche de vehiculo
	 */
	private Gmarker buildMarker(Viaje v) {
		Gmarker gmarker = new Gmarker();
		if (v.estaPendiente()) {
			// Pongo la casita en el origen del viaje
			gmarker.setId(GMARK_PREFIX + v.getIdViaje());
			gmarker.setIconImage(IMAGES__MARKER_HOUSE);
			gmarker.setDraggable(null);
			gmarker.setLat(v.getOrigenLatitud());
			gmarker.setLng(v.getOrigenLongitud());
		} else if (v.estaAsignado() || v.estaTransportando()) {
			// Siga ese vehiculo!!!
			gmarker.setId(GMARK_PREFIX + v.getVehiculo().getPatente());
			gmarker.setIconImage(IMAGES__VEHICULO_OCUPADO_MARKER);
			gmarker.setDraggable(null);
			gmarker.setLat(v.getVehiculo().getLat());
			gmarker.setLng(v.getVehiculo().getLng());
		}
		return gmarker;
	}

	/**
	 * Crea un gmarker para un vehiculo libre
	 */
	private Gmarker buildMarker(Vehiculo vehiculoLibre) {
		Gmarker gmarker = new Gmarker();
		gmarker.setId(GMARK_PREFIX + vehiculoLibre.getPatente());
		gmarker.setIconImage(IMAGES__VEHICULO_MARKER);
		gmarker.setDraggable(null);
		gmarker.setLat(vehiculoLibre.getLat());
		gmarker.setLng(vehiculoLibre.getLng());
		return gmarker;
	}

}
