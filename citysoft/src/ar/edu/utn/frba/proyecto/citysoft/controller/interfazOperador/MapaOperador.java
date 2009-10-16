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
	private Map<Viaje, Gmarker> mapPedidosMarcados = new HashMap<Viaje, Gmarker>();
	private Object mapaCentradoEn;

	// **************************************
	// ** CENTRO DE MAPA
	// **************************************

	public void setMapaCentradoEn(Taxi taxiLibre) {
		this.mapaCentradoEn = taxiLibre;
	}

	public void setMapaCentradoEn(Viaje viaje) {
		this.mapaCentradoEn = viaje;
	}

	public void centrarMapa() {
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
			this.mapViajesSeguidos.put(v, buildMarker(v));
		} else {
			this.mapViajesSeguidos.remove(v);
		}
	}

	public void setTaxiSeguido(Taxi t, boolean seguir) {
		t.validarTaxiLibre();
		if (seguir) {
			this.mapTaxisSeguidos.put(t, buildMarker(t));
		} else {
			this.mapTaxisSeguidos.remove(t);
		}
	}

	public void setPedidoMarcado(Viaje v, boolean marcar) {
		v.validarViajePendiente();
		if (marcar) {
			this.mapPedidosMarcados.put(v, buildMarker(v));
		} else {
			this.mapPedidosMarcados.remove(v);
		}
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
	 * Dice si el pedido pendiente está actualmente marcado en el mapa
	 */
	public boolean estaMarcadoElPedido(Viaje v) {
		return this.mapPedidosMarcados.containsKey(v);
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
