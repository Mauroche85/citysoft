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

	/**
	 * NO USAR. Usar en cambio {@link CityMarcador#serasCentro(CityMapa)}
	 */
	public void tendrasComoCentroA(CityMarcador marca) {
		this.marcadorQueMeCentra = marca;
	}

	public CityMarcador quienMeCentra() {
		return this.marcadorQueMeCentra;
	}

}
