package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcadorFactory;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityPolilinea;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

/**
 * Este objeto representa la relacion entre un vehiculo y los dibujos en un mapa
 * que se relacionen con dicho vehiculo.
 */
public class PloteableDesdeVehiculo extends ObjetoPlotteable {

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public ObjetoDeDominio getObjetoDelModelo() {
		return this.vehiculo;
	}

	public CityMarcador getMarcadorCentral() {
		return getMarcadorPosicion();
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = new ArrayList<CityMarcador>();
		l.add(getMarcadorPosicion());
		return l;
	}

	@Override
	public List<CityPolilinea> getPolilineas() {
		return new ArrayList<CityPolilinea>();
	}

	@Override
	public void actualizarPlotting() {
		this.marcadorPosicion.setLat(this.vehiculo.getLat());
		this.marcadorPosicion.setLng(this.vehiculo.getLng());
	}

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	private Vehiculo vehiculo;
	private CityMarcador marcadorPosicion;

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeVehiculo(Vehiculo v) {
		this.vehiculo = v;
	}

	// **************************************
	// ** EJECUCION
	// **************************************

	public CityMarcador getMarcadorPosicion() {
		if (this.marcadorPosicion == null) {
			this.marcadorPosicion = CityMarcadorFactory.buildMarcadorVehiculo(this.vehiculo);
		}
		return this.marcadorPosicion;
	}

}