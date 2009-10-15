package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Timer;

public class InterfazOperadorTimer extends Timer {

	private static final long serialVersionUID = 5608357073418728915L;

	private VentanaInterfazOperador win;

	// **************************************
	// ** Execution
	// **************************************

	public void ring() {
		if (this.win == null) {
			this.win = (VentanaInterfazOperador) this.getParent();
		}
		Event event = new Event(Events.ON_CHANGE, this.win);
		Events.sendEvent(event);
	}

}
