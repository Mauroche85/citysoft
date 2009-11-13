package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import org.zkoss.gmaps.Gmaps;

/**
 * Es un mapa de google customizado para lo que necesitamos
 * 
 * @see CityVentanaConMapa
 * @see CityMarcador
 */
public class CityMapa extends Gmaps {

	private CityMarcador marcadorQueMeCentra;

	// **************************************
	// ** EXECUTION
	// **************************************

	public void tendrasComoCentroA(CityMarcador marca) {
		this.marcadorQueMeCentra = marca;
		actualizarPosicionCentral();
	}

	public void descentrar() {
		this.marcadorQueMeCentra = null;
	}

	public CityMarcador quienMeCentra() {
		return this.marcadorQueMeCentra;
	}

	public void actualizarPosicionCentral() {
		if (this.marcadorQueMeCentra != null) {
			setLat(this.marcadorQueMeCentra.getLat());
			setLng(this.marcadorQueMeCentra.getLng());
		}
	}

}
