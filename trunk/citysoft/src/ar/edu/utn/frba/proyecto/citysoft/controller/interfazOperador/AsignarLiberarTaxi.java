package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class AsignarLiberarTaxi extends Window {

	private static final long serialVersionUID = -30629984537535030L;

	public void asignarTaxi() {
		Viaje v = CentralTaxis.getInstance().getViaje(
				Integer.parseInt(elemIdViajePendiente().getValue()));
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());

		// Setea Taxi en el viaje
		v.asignar(t);

		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central de taxis
		CentralTaxis.getInstance().addTaxi(t);
		CentralTaxis.getInstance().addViaje(v);
		this.cerrar();

	}

	public void liberarTaxi() {
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());

		// TODO: hay que trabajar la liberacion de taxi
		// t.setActivado(false); << esto entiendo no es liberacion, sino
		// desactivacion

		// TODO la liberacion es la finalizcion del viaje. Ir por ese lado

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

	public Textbox elemIdViajePendiente() {
		return (Textbox) this.getFellow("idViajePendiente");
	}

}