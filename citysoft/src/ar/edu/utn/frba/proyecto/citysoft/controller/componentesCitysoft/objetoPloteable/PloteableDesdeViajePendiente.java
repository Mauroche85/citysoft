package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public class PloteableDesdeViajePendiente extends PloteableDesdeViaje {

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViajePendiente(Viaje v) {
		super(v);
	}

}
