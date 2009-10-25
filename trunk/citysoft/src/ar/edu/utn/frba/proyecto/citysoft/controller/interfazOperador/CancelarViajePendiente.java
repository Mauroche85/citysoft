package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class CancelarViajePendiente extends Window {

	private static final long serialVersionUID = 7385723048921493133L;

	public void cancelarViajePendiente() {
		Viaje v = Central.getInstance().getViaje(
				Integer.parseInt(elemIdViajePendiente().getValue()));

		// Cancela viaje pendiente
		v.cancelar();

		Central.getInstance().addViaje(v);
		this.cerrar();

	}

	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Helpers
	// **************************************

	public Textbox elemIdViajePendiente() {
		return (Textbox) this.getFellow("txtIdViaje");
	}

}