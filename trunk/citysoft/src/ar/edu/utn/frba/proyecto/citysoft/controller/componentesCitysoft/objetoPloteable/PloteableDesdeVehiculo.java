package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcadorFactory;
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
	public Object getObjetoDelModelo() {
		return this.vehiculo;
	}

	@Override
	public void mostrarMarcadores(CityMapa nuevoMapa) {
		// Si tengo un mapa viejo, quito el marker de posicion
		if (this.tengoMapa()) {
			this.marcadorPosicion.detach();
		}
		// Si no existe el marcador, se crea solo
		getMarcadorPosicion().setParent(nuevoMapa);
		// Ahora si... declaramos el nuevo mapa!
		this.mapa = nuevoMapa;
	}

	/**
	 * La interfaz nos dice bien clarito que debemos perder el rastro de los
	 * marcadores
	 */
	@Override
	public void quitarMarcadoresDelMapa() {
		this.marcadorPosicion.detach();
		// Perdemos las referencias a lo que creiamos era importante
		this.marcadorPosicion = null;
		this.mapa = null;
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = new ArrayList<CityMarcador>();
		l.add(this.marcadorPosicion);
		return l;
	}

	@Override
	public void actualizarPlotting() {
		this.marcadorPosicion.setLat(this.vehiculo.getLat());
		this.marcadorPosicion.setLng(this.vehiculo.getLng());
	}

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	private CityMapa mapa;
	private Vehiculo vehiculo;
	private CityMarcador marcadorPosicion;

	// **************************************
	// ** EJECUCION
	// **************************************

	/**
	 * O tenemos el conjunto correspondiente de marcadores y el mapa, o no
	 * tenemos nada!!!
	 */
	private boolean tengoMapa() {
		if (this.mapa != null) {
			validarQueExisteElMarcadorYElMapa();
			return true;
		}
		return false;
	}

	public CityMarcador getMarcadorPosicion() {
		if (this.marcadorPosicion == null) {
			this.marcadorPosicion = CityMarcadorFactory.buildMarcadorVehiculo(this.vehiculo);
		}
		return this.marcadorPosicion;
	}

	// **************************************
	// ** HELPERS
	// **************************************

	/**
	 * O tenemos el conjunto correspondiente de marcadores y el mapa, o no
	 * tenemos nada!!! Con esto le ponemos la firma. Sirve para asegurarse de
	 * que no ocurra ninguna situacion anomala
	 */
	private void validarQueExisteElMarcadorYElMapa() {
		if (this.mapa == null) {
			throw new RuntimeException("El objeto ploteable correspondiente al viaje"
					+ " carece de mapa");
		} else if (this.marcadorPosicion == null) {
			throw new RuntimeException("El objeto ploteable correspondiente al viaje"
					+ " carece del marcador de posicion");
		}
	}

}