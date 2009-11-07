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
		return m;
	}

	public static CityMarcador buildMarcadorDestino(Viaje v) {
		CityMarcador m = new CityMarcador();
		m.setLat(v.getDestinoLatitud());
		m.setLng(v.getDestinoLongitud());
		m.setIconImage(IMAGES__MARKER_FLAG);
		return m;
	}

	public static CityMarcador buildMarcadorTransportando(Viaje v) {
		return buildMarcadorVehiculo(v.getVehiculo());
	}

	public static CityMarcador buildMarcadorVehiculo(Vehiculo v) {
		CityMarcador m = new CityMarcador();
		m.setLat(v.getLat());
		m.setLng(v.getLng());
		m.setIconImage(IMAGES__VEHICULO_MARKER);
		return m;
	}

}
