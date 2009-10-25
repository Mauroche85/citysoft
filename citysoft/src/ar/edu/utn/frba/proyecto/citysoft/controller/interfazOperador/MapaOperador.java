package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class MapaOperador extends Gmaps implements ConstantesInterfazOperador {

	private static final long serialVersionUID = -6646990321890527463L;

	// **************************************
	// ** ATTRIBUTES
	// **************************************

	private Map<Viaje, Gmarker> mapViajesSeguidos = new HashMap<Viaje, Gmarker>();
	private Map<Taxi, Gmarker> mapTaxisSeguidos = new HashMap<Taxi, Gmarker>();
	private Map<Viaje, Gmarker> mapViajesPendientesMarcados = new HashMap<Viaje, Gmarker>();
	private Object mapaCentradoEn;

	// **************************************
	// ** CENTRO DE MAPA
	// **************************************

	public Object getMapaCentradoEn() {
		return this.mapaCentradoEn;
	}

	public void setMapaCentradoEn(Taxi taxiLibre) {
		this.mapaCentradoEn = taxiLibre;
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
		if (this.mapaCentradoEn.getClass().isAssignableFrom(Taxi.class)) {
			Taxi t = (Taxi) this.mapaCentradoEn;
			setLat(t.getLat());
			setLng(t.getLng());
		} else if (this.mapaCentradoEn.getClass().isAssignableFrom(Viaje.class)) {
			Viaje v = (Viaje) this.mapaCentradoEn;
			if (v.estaPendiente()) {
				setLat(v.getOrigenLatitud());
				setLng(v.getOrigenLongitud());
			} else {
				setLat(v.getTaxi().getLat());
				setLng(v.getTaxi().getLng());
			}
		} else {
			throw new RuntimeException("Mapa centrado en algo desconocido (" + this.mapaCentradoEn
					+ ")");
		}
	}

	// **************************************
	// ** MARCAR/DESMARCAR EN MAPA
	// **************************************

	public void setViajeSeguido(Viaje v, boolean seguir) {
		v.validarViajeEnCurso();
		if (seguir) {
			Gmarker marker = buildMarker(v);
			marker.setParent(this);
			this.mapViajesSeguidos.put(v, marker);
			setMapaCentradoEn(v);
		} else {
			if (v.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapViajesSeguidos.remove(v));
		}
		VentanaInterfazOperador.refrescarVentana(this);
	}

	public void setTaxiSeguido(Taxi t, boolean seguir) {
		t.validarTaxiLibre();
		if (seguir) {
			Gmarker marker = buildMarker(t);
			marker.setParent(this);
			this.mapTaxisSeguidos.put(t, marker);
			setMapaCentradoEn(t);
		} else {
			if (t.equals(this.mapaCentradoEn))
				this.mapaCentradoEn = null;
			removeChild(this.mapTaxisSeguidos.remove(t));
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

	// **************************************
	// ** PREGUNTAR SI ALGO ESTA MARCADO EN EL MAPA
	// **************************************

	/**
	 * Dice si el viaje asignado o en curso en cuestión, está siendo seguido en
	 * el mapa
	 */
	public boolean estaSiendoSeguidoElViaje(Viaje v) {
		return this.mapViajesSeguidos.containsKey(v);
	}

	/**
	 * Dice si el taxi libre en cuestion está siendo seguido en el mapa
	 */
	public boolean estaSiendoSeguidoElTaxi(Taxi t) {
		return this.mapTaxisSeguidos.containsKey(t);
	}

	/**
	 * Dice si el viaje pendiente está actualmente marcado en el mapa
	 */
	public boolean estaMarcadoElViajePendiente(Viaje v) {
		return this.mapViajesPendientesMarcados.containsKey(v);
	}

	// **************************************
	// ** REFRESCAR!!! (Esta es la funcion mas importante)
	// **************************************

	public void actualizarPosicionMarkers() {
		// Viajes en curso
		for (Viaje v : this.mapViajesSeguidos.keySet()) {
			Gmarker gm = this.mapViajesSeguidos.get(v);
			actualizarMarker(gm, v);
		}
		// Taxis libres
		for (Taxi t : this.mapTaxisSeguidos.keySet()) {
			Gmarker gm = this.mapTaxisSeguidos.get(t);
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
			gm.setLat(v.getTaxi().getLat());
			gm.setLng(v.getTaxi().getLng());
		}
	}

	private void actualizarMarker(Gmarker gm, Taxi taxi) {
		gm.setLat(taxi.getLat());
		gm.setLng(taxi.getLng());
	}

	// **************************************
	// ** Helpers
	// **************************************

	/**
	 * Crea un gmarker desde un viaje pendiente o en curso (asignado o en
	 * transcurso). <li>Si el viaje esta pendiente, pongo casita fija <li>Si el
	 * viaje esta en curso, pongo pinche de taxi
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
			gmarker.setId(GMARK_PREFIX + v.getTaxi().getPatente());
			gmarker.setIconImage(IMAGES__TAXI_OCUPADO_MARKER);
			gmarker.setDraggable(null);
			gmarker.setLat(v.getTaxi().getLat());
			gmarker.setLng(v.getTaxi().getLng());
		}
		return gmarker;
	}

	/**
	 * Crea un gmarker para un taxi libre
	 */
	private Gmarker buildMarker(Taxi taxiLibre) {
		Gmarker gmarker = new Gmarker();
		gmarker.setId(GMARK_PREFIX + taxiLibre.getPatente());
		gmarker.setIconImage(IMAGES__TAXI_MARKER);
		gmarker.setDraggable(null);
		gmarker.setLat(taxiLibre.getLat());
		gmarker.setLng(taxiLibre.getLng());
		return gmarker;
	}

}
