package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public class PloteableDesdeViajeAsignado extends PloteableDesdeViaje {

	// **************************************
	// ** INTERFAZ
	// **************************************

	public CityMarcador getMarcadorCentral() {
		return getMarcadorAsignado();
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = super.getMarcadores();
		l.add(getMarcadorAsignado());
		return l;
	}

	@Override
	public void actualizarPlotting() {
		super.actualizarPlotting();
		actualizarPlottingAsignado();
	}

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViajeAsignado(Viaje v) {
		super(v);
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void actualizarPlottingAsignado() {
		this.marcadorAsignado.setLat(this.viaje.getVehiculo().getLat());
		this.marcadorAsignado.setLng(this.viaje.getVehiculo().getLng());
	}

}
