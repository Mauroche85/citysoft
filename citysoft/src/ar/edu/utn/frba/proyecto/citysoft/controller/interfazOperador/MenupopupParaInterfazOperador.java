package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.api.Label;
import org.zkoss.zul.api.Listcell;

public class MenupopupParaInterfazOperador extends Menupopup {

	private static final long serialVersionUID = 3647165903702583333L;

	public MenupopupParaInterfazOperador() {
		this.addEventListener(Events.ON_OPEN, new OnOpenGuardarQuienAbrioElPopup());
	}

	// **************************************
	// ** INNER CLASS. ¿¿¿Servirá para extenderla hacia afuera???
	// **************************************

	private class OnOpenGuardarQuienAbrioElPopup implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			OpenEvent openEvent = (OpenEvent) event;
			if (openEvent.isOpen()) {
				Listcell cellWithValue = (Listcell) openEvent.getReference();
				openEvent.getTarget().setAttribute("quienAbrioElPopup", cellWithValue.getValue());
			}
		}
	}

}
