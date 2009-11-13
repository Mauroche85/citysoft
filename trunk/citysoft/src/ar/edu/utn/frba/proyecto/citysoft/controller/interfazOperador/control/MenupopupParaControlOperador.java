package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador.control;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.Menupopup;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ListitemOperadorPloteableBasico;

public class MenupopupParaControlOperador extends Menupopup implements ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = 3647165903702583333L;

	public MenupopupParaControlOperador() {
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
				ListitemOperadorPloteableBasico item = (ListitemOperadorPloteableBasico) openEvent
						.getReference();
				Executions.getCurrent().getDesktop().setAttribute(
						CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP, item.getObjetoPloteable());
			}
		}
	}

}
