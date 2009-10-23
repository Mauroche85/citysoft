package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class CancelarPedido extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7385723048921493133L;

	
	public void cancelarPedidoPend(){
		Viaje v = CentralTaxis.getInstance().getViaje(Integer.parseInt(elemNumPedido().getValue()));
		
		// Cancela Pedido
		v.cancelar();

		CentralTaxis.getInstance().addViaje(v);
		this.cerrar();

	}	

	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Helpers
	// **************************************
	
	public Textbox elemNumPedido() {
		return (Textbox) this.getFellow("pedidoPendiente");
	}

}