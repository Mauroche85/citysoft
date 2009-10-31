package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zul.Listcell;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Esta celda puede representar tanto a un viaje como un vehiculo, o lo que
 * sea!!!
 */
public class CeldaQueSabeSiEstaSiendoSeguida extends Listcell implements ConstantesInterfazOperador {

	private static final long serialVersionUID = 2808462708004166362L;

	// **************************************
	// ** EJECUCION
	// **************************************

	/**
	 * Indica si el mapa esta centrado sobre esta celda
	 */
	public boolean mapaCentradoEnMi() {
		return getValue().equals(elemMapa().getMapaCentradoEn());
	}

	/**
	 * Indica si el mapa está siguiendo al viaje (ya sea pendiente o en curso)
	 * que representa esta celda
	 */
	public boolean estaSiendoSeguidoElViajeBajoTransporte() {
		return elemMapa().estaSiendoSeguidoElViajeBajoTransporte(viajeSeleccionado());
	}

	public boolean estaSiendoSeguidoElViajeAsignado() {
		return elemMapa().estaSiendoSeguidoElViajeAsignado(viajeSeleccionado());
	}

	public boolean estaMarcadoElViajePendiente() {
		return elemMapa().estaMarcadoElViajePendiente(viajeSeleccionado());
	}

	/**
	 * Indica si el map esta siguiendo al vehiculo represetnado por esta celda
	 */
	public boolean estaSiendoSeguidoElVehiculo() {
		return elemMapa().estaSiendoSeguidoElVehiculo(vehiculoSeleccionado());
	}

	// **************************************
	// ** HELPERS
	// **************************************

	public MapaOperador elemMapa() {
		return (MapaOperador) this.getFellow(COMP__GMAP_OPERADOR);
	}

	/**
	 * Seleccionado en el sentido de que le hicieron click derecho y estamos en
	 * el menu contextual de este viaje
	 */
	public Viaje viajeSeleccionado() {
		int idViaje = (Integer) getValue();
		return Central.getInstance().getViaje(idViaje);
	}

	/**
	 * Seleccionado en el sentido de que le hicieron click derecho y estamos en
	 * el menu contextual de este vehiculo
	 */
	public Vehiculo vehiculoSeleccionado() {
		String patente = (String) getValue();
		return Central.getInstance().getVehiculoPorPatente(patente);
	}

}
