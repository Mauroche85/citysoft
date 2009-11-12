package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class AsignarLiberarVehiculo extends Window {

	private static final long serialVersionUID = -30629984537535030L;

	public void asignarVehiculo() throws InterruptedException {
		
		Viaje v = Central.getInstance().getViaje(Integer.parseInt(elemIdViajePendiente().getValue()));
		Vehiculo t = Central.getInstance().getVehiculoPorPatente(elemPatVehiculo().getValue());
		
		if (v == null)
		{ 
			Messagebox.show("No se encuentra el Pedido indicado.", "Importante", Messagebox.OK, Messagebox.EXCLAMATION);
		} 
		else if (t == null)
		{
			Messagebox.show("No se encuentra la Patente indicada.", "Importante", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else
		{
			// Setea vehiculo en el viaje
			v.asignar(t);
	
			// Cuando se modifica alguna entidad, hay que volverla a agregar en la
			// central
			Central.getInstance().addVehiculo(t);
			Central.getInstance().addViaje(v);
			this.cerrar();
		}
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