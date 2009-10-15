package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

public class ActivarDesactivarTaxi extends Window {

	private static final long serialVersionUID = 7125424930429094431L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public void activarTaxi() {
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());
		t.setActivado(true);
		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central de taxis
		CentralTaxis.getInstance().addTaxi(t);
		this.cerrar();

	}

	public void desactivarTaxi() {
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());
		t.setActivado(false);
		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central de taxis
		CentralTaxis.getInstance().addTaxi(t);
		this.cerrar();

	}

	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Helpers
	// **************************************

	public Textbox elemIdTaxi() {
		return (Textbox) this.getFellow("idTaxi");
	}

}
