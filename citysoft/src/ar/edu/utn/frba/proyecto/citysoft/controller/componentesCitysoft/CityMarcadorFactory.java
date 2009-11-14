package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class CityMarcadorFactory implements ConstantesGeneralesDeVentanas {

	public static CityMarcador buildMarcadorOrigen(Viaje v) {
		CityMarcador m = new CityMarcador();
		m.setLat(v.getOrigenLatitud());
		m.setLng(v.getOrigenLongitud());
		m.setIconImage(IMAGES__MARKER_HOUSE);
		m.setTooltiptext(v.getDescripcionOrigen());
		return m;
	}

	public static CityMarcador buildMarcadorDestino(Viaje v) {
		CityMarcador m = new CityMarcador();
		m.setLat(v.getDestinoLatitud());
		m.setLng(v.getDestinoLongitud());
		m.setIconImage(IMAGES__MARKER_FLAG);
		m.setTooltiptext(v.getDescripcionDesitno());
		return m;
	}

	public static CityMarcador buildMarcadorAsignado(Viaje v) {
		return buildMarcadorVehiculo(v.getVehiculo());
	}

	public static CityMarcador buildMarcadorTransportando(Viaje v) {
		return buildMarcadorVehiculo(v.getVehiculo());
	}

	public static CityMarcador buildMarcadorVehiculo(Vehiculo v) {
		CityMarcador m = new CityMarcador();
		m.setLat(v.getLat());
		m.setLng(v.getLng());
		if (v.estoyLibre())
			m.setIconImage(IMAGES__VEHICULO_MARKER);
		else
			m.setIconImage(IMAGES__VEHICULO_OCUPADO_MARKER);
		m.setTooltiptext(v.getPatente());
		return m;
	}

}
