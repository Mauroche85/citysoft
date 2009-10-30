package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class ActivarDesactivarVehiculo extends Window {

	private static final long serialVersionUID = 7125424930429094431L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public void activarVehiculo() {
		Vehiculo t = Central.getInstance().getVehiculoPorPatente(elemPatVehiculo().getValue());
		t.setActivado(true);
		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central
		Central.getInstance().addVehiculo(t);
		this.cerrar();

	}

	public void desactivarVehiculo() {
		Vehiculo t = Central.getInstance().getVehiculoPorPatente(elemPatVehiculo().getValue());
		t.setActivado(false);
		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central
		Central.getInstance().addVehiculo(t);
		this.cerrar();

	}

	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Helpers
	// **************************************

	public Textbox elemPatVehiculo() {
		return (Textbox) this.getFellow("patVehiculo");
	}

}
