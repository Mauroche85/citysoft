package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityPolilinea;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public class PloteableDesdeViajeCompleto extends PloteableDesdeViaje {

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public List<CityPolilinea> getPolilineas() {
		List<CityPolilinea> l = super.getPolilineas();
		l.add(getPolilineaRuta());
		return l;
	}

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViajeCompleto(Viaje v) {
		super(v);
	}

}
