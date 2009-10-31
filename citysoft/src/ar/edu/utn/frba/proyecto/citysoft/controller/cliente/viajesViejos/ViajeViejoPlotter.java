package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.viajesViejos;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.controller.utils.GmapsUtils;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Esta clase debería encargarse de dibujar un viaje viejo
 * 
 * @author Hugo
 */
public class ViajeViejoPlotter {

	public void plot(Viaje v, Gmaps map) {
		if (v.fueCompletado()) {
			doPlotCompletado(v, map);
		} else if (v.fueCancelado()) {
			doPlotCancelado(v, map);
		} else {
			throw new RuntimeException(
					"Solo se pueden plotear viajes terminados o solicitudes canceladas");
		}
	}

	private void doPlotCompletado(Viaje v, Gmaps map) {
		doPlotOrigenYDestino(v, map);
		doPlotRecorrido(v, map);
	}

	private void doPlotCancelado(Viaje v, Gmaps map) {
		doPlotOrigenYDestino(v, map);
	}

	private void doPlotOrigenYDestino(Viaje v, Gmaps map) {
		Gmarker orig = GmapsUtils.buildMarkerOrigen(v);
		Gmarker dest = GmapsUtils.buildMarkerDestino(v);
		orig.setParent(map);
		dest.setParent(map);
	}

	private void doPlotRecorrido(Viaje v, Gmaps map) {
		// TODO Auto-generated method stub
	}

}
