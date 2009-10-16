package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Menupopup;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class VentanaInterfazOperador extends Window implements ConstantesInterfazOperador {

	private static final long serialVersionUID = -6715263410797692543L;

	// **************************************
	// ** ELEMENTOS
	// **************************************

	public MapaOperador getMapa() {
		return (MapaOperador) this.getFellow(COMP__GMAP_OPERADOR);
	}

	// **************************************
	// ** MODELOS
	// **************************************

	public Collection<Viaje> getListaViajesEnCurso() {
		SortedSet<Viaje> lista = new TreeSet<Viaje>(CentralTaxis.getInstance().getViajesEnCurso());
		return lista;
	}

	public Collection<Taxi> getListaVehiculosLibres() {
		SortedSet<Taxi> lista = new TreeSet<Taxi>(CentralTaxis.getInstance().getTaxisLibres());
		return lista;
	}

	public Collection<Viaje> getListaPedidosPendientes() {
		SortedSet<Viaje> lista = new TreeSet<Viaje>(CentralTaxis.getInstance().getViajesPendientes());
		return lista;
	}

	public Collection<Taxi> getListaVehiculosDesactivados() {
		SortedSet<Taxi> lista = new TreeSet<Taxi>(CentralTaxis.getInstance().getTaxisDesactivados());
		return lista;
	}

	// **************************************
	// ** ACCIONES SOBRE LISTAS
	// **************************************

	public void abrirFinalizacionDeViaje(Menupopup popup) {
		// TODO la liberacion es la finalizcion del viaje. Ir por ese lado
		Window win = (Window) Executions.createComponents(ZUL__LIBERAR_TAXI, null, null);
		agregarRefrescoAlCierre(win);
		((Textbox) win.getFellow("idTaxi")).setValue((String) popup
				.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirAsignacionPorTaxiLibre(Menupopup popup) {
		VentanitaAsignacionTaxi win = (VentanitaAsignacionTaxi) Executions.createComponents(
				ZUL__ASIGNAR_TAXI, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatente().setValue((String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirAsignacionPorViaje(Menupopup popup) {
		VentanitaAsignacionTaxi win = (VentanitaAsignacionTaxi) Executions.createComponents(
				ZUL__ASIGNAR_TAXI, null, null);
		agregarRefrescoAlCierre(win);
		win.elemViaje().setValue((String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirCancelacionViaje(Menupopup popup) {
		// TODO hacer que esto funcione
		Window win = (Window) Executions.createComponents(ZUL__CANCELAR_PEDIDO, null, null);
		agregarRefrescoAlCierre(win);
		((Textbox) win.getFellow("pedidoPendiente")).setValue((String) popup
				.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirActivacionTaxi(Menupopup popup) {
		ActivarDesactivarTaxi win = (ActivarDesactivarTaxi) Executions.createComponents(
				ZUL__ACTIVAR_TAXI, null, null);
		agregarRefrescoAlCierre(win);
		win.elemIdTaxi().setValue((String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirDesactivacionTaxi(Menupopup popup) {
		ActivarDesactivarTaxi win = (ActivarDesactivarTaxi) Executions.createComponents(
				"desactivarTaxi.zul", null, null);
		agregarRefrescoAlCierre(win);
		win.elemIdTaxi().setValue((String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	// **************************************
	// ** Helpers
	// **************************************

	/**
	 * Esta funcion es la encargada de agregar a la nueva ventana, la rutina que
	 * manda a refrescar la interfaz del operador a activarse en cuanto se
	 * cierre la nueva ventana en cuestion.
	 * 
	 * Sirve, por ejemplo, para decir que cuando cierren la ventana
	 * "desactivar taxi", entonces debe refrescarse la interfaz del operador. El
	 * resultado, es que se actualizan las listas.
	 */
	private void agregarRefrescoAlCierre(Window win) {
		win.addEventListener(Events.ON_CLOSE, new OnCloseRefrescarVentana(this));
	}

	// **************************************
	// ** INNER CLASS. ¿¿¿Servirá para extenderla hacia afuera???
	// **************************************

	private class OnCloseRefrescarVentana implements EventListener {
		private VentanaInterfazOperador win;

		public OnCloseRefrescarVentana(VentanaInterfazOperador win) {
			this.win = win;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			this.refrescarVentana();
		}

		public void refrescarVentana() {
			Event event = new Event(Events.ON_CHANGE, this.win);
			Events.sendEvent(event);
		}
	}

}
