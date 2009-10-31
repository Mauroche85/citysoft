package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.follower;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.api.Listbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class FollowerWindowUtils {

	/**
	 * Dispara el evento onChange en la lista de vehiculos seguidos, para que se
	 * refresque. Este método debe dispararse desde los procesos que generan un
	 * seguimiento o lo eliminan.
	 */
	public static void dispararCambiosEnLista(Component unComponente) {
		Listbox listbox = obtenerComponenteListaDeSeguimiento(unComponente);
		Event event = new Event(Events.ON_CHANGE, listbox);
		Events.sendEvent(event);
	}

	public static boolean seguimientoCentradoSobreVehiculo(Vehiculo t, Component unComponente) {
		Listbox listbox = obtenerComponenteListaDeSeguimiento(unComponente);
		ItemDeListaSeguimiento item = (ItemDeListaSeguimiento) listbox.getSelectedItemApi();
		if (item != null) {
			Vehiculo vehiculoSeleccionado = Central.getInstance().getVehiculoPorPatente(
					(String) item.getValue());
			return vehiculoSeleccionado.equals(t);
		} else
			return false;
	}

	// **************************************
	// ** Helpers
	// **************************************

	private static Listbox obtenerComponenteListaDeSeguimiento(Component unComponente) {
		return (Listbox) unComponente.getFellow(FollowerWindowComponents.LIST_VIAJES_SEGUIDOS);
	}

}
