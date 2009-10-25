package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zul.Listcell;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Esta celda puede representar tanto a un viaje como un taxi, o lo que sea!!!
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
	public boolean estaSiendoSeguidoElViaje() {
		return elemMapa().estaSiendoSeguidoElViaje((Viaje) getValue());
	}

	/**
	 * Indica si el map esta siguiendo al taxi represetnado por esta celda
	 */
	public boolean estaSiendoSeguidoElTaxi() {
		return elemMapa().estaSiendoSeguidoElTaxi((Taxi) getValue());
	}

	public boolean estaMarcadoElViajePendiente() {
		return elemMapa().estaMarcadoElViajePendiente((Viaje) getValue());
	}

	// **************************************
	// ** HELPERS
	// **************************************

	public MapaOperador elemMapa() {
		return (MapaOperador) this.getFellow(COMP__GMAP_OPERADOR);
	}

}
