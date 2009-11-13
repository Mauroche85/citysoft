package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import org.zkoss.gmaps.Gpolyline;

/**
 * Es una polilinea para el mapa de google, pero customizado.
 */
public class CityPolilinea extends Gpolyline {

	private static final long serialVersionUID = -8380283141342658783L;

	// **************************************
	// ** ESPECIALIZAZCION
	// **************************************

	private boolean isDirty;

	@Override
	public void detach() {
		super.detach();
		this.isDirty = true;
	}

	public boolean isDirty() {
		return this.isDirty;
	}

}
