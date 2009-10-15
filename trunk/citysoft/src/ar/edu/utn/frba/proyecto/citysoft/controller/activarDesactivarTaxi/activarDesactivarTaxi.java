package ar.edu.utn.frba.proyecto.citysoft.controller.activarDesactivarTaxi;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

public class activarDesactivarTaxi extends Window {
	
	private static final long serialVersionUID = 7125424930429094431L;
	
	private Textbox elemIdTaxi() {
		return (Textbox) this.getFellow("idTaxi");
	}

	public void activarTaxi() {
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());
		
		t.setActivado(true);
		
		this.cerrar();
		
	}
	
	public void desactivarTaxi() {
		Taxi t = CentralTaxis.getInstance().getTaxiPorPatente(elemIdTaxi().getValue());
		
		t.setActivado(false);
		
		this.cerrar();
		
	}
	
	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}
	
/*
	public void modifCliente() {
		// Creo el cliente!!!
		Cliente c = CentralTaxis.getInstance().getClientePorId(elemLegajo().getValue());

		c.setApellido(elemApellido().getValue());
		c.setNombre(elemNombres().getValue());
		// c.setTipoDocumento(elemTipoDocumento().getValue());
		c.setDni(elemNroDocumento().getValue());
		c.setDireccion(elemDireccion().getValue());
		c.setLocalidad(elemLocalidad().getValue());
		c.setTelefono(elemTelefono().getValue());

		// Actualizo el cliente en la central!!!!
		CentralTaxis.getInstance().addCliente(c);

		// Cierro la ventana
		this.cerrar();
	}
	
	*/

}
