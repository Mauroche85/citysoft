package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

public class VentanitaAsignacionTaxi extends Window {

	private static final long serialVersionUID = -55891640351493459L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Helpers
	// **************************************

	public Textbox elemViaje() {
		return (Textbox) this.getFellow("pedidoPendiente");
	}

	public Textbox elemPatente() {
		return (Textbox) this.getFellow("idTaxi");
	}

}
