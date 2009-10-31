package ar.edu.utn.frba.proyecto.citysoft.controller.utils;

import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class GmapsUtils implements ConstantesGeneralesDeVentanas {

	public static Gmarker buildMarkerOrigen(Viaje v) {
		Gmarker m = new Gmarker();
		m.setLat(v.getOrigenLatitud());
		m.setLng(v.getOrigenLongitud());
		m.setDraggingEnabled(false);
		m.setIconImage(IMAGES__MARKER_HOUSE);
		return m;
	}

	public static Gmarker buildMarkerDestino(Viaje v) {
		Gmarker m = new Gmarker();
		m.setLat(v.getDestinoLatitud());
		m.setLng(v.getDestinoLongitud());
		m.setDraggingEnabled(false);
		m.setIconImage(IMAGES__MARKER_FLAG);
		return m;
	}

	
}
