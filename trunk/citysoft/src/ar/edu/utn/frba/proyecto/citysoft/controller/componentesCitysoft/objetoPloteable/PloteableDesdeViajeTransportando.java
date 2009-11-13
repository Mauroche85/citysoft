package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public class PloteableDesdeViajeTransportando extends PloteableDesdeViaje {

	// **************************************
	// ** INTERFAZ
	// **************************************

	public CityMarcador getMarcadorCentral() {
		return getMarcadorTransportando();
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = super.getMarcadores();
		l.add(getMarcadorTransportando());
		return l;
	}

	@Override
	public void actualizarPlotting() {
		super.actualizarPlotting();
		actualizarPlottingTransportando();
	}

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViajeTransportando(Viaje v) {
		super(v);
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void actualizarPlottingTransportando() {
		this.marcadorTransportando.setLat(this.viaje.getVehiculo().getLat());
		this.marcadorTransportando.setLng(this.viaje.getVehiculo().getLng());
	}

}
