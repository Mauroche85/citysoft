package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class AsignarLiberarVehiculo extends Window {

	private static final long serialVersionUID = -30629984537535030L;

	public void asignarVehiculo() {
		Viaje v = Central.getInstance().getViaje(Integer.parseInt(elemIdViajePendiente().getValue()));
		Vehiculo t = Central.getInstance().getVehiculoPorPatente(elemPatVehiculo().getValue());

		// Setea vehiculo en el viaje
		v.asignar(t);

		// Cuando se modifica alguna entidad, hay que volverla a agregar en la
		// central
		Central.getInstance().addVehiculo(t);
		Central.getInstance().addViaje(v);
		this.cerrar();

	}

	public void liberarVehiculo() {
		Vehiculo t = Central.getInstance().getVehiculoPorPatente(elemPatVehiculo().getValue());

		// TODO: hay que trabajar la liberacion de vechiulos
		// t.setActivado(false); << esto entiendo no es liberacion, sino
		// desactivacion

		// TODO la liberacion es la finalizcion del viaje. Ir por ese lado

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

	public Textbox elemIdViajePendiente() {
		return (Textbox) this.getFellow("idViajePendiente");
	}

}