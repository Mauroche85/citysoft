package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class ObjetoPlotteableFactory {

	public static ObjetoPlotteable crearObjetoPloteable(ObjetoDeDominio d) {
		if (d instanceof Viaje) {
			Viaje v = (Viaje) d;
			if (v.estaPendiente())
				return new PloteableDesdeViajePendiente(v);
			else if (v.estaAsignado())
				return new PloteableDesdeViajeAsignado(v);
			else if (v.estaTransportando())
				return new PloteableDesdeViajeTransportando(v);
			else if (v.fueCompletado())
				return new PloteableDesdeViajeCompleto(v);
			else
				throw new RuntimeException(
						"Se intenta plotear un viaje bajo un estado no soportado (" + v + ")");
		}
		if (d instanceof Vehiculo)
			return new PloteableDesdeVehiculo((Vehiculo) d);
		else
			throw new RuntimeException("No se conoce el dominio del objeto " + d);
	}

}
