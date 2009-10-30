package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Menupopup;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class VentanaInterfazOperador extends Window implements ConstantesInterfazOperador {

	private static final long serialVersionUID = -6715263410797692543L;

	public VentanaInterfazOperador() {
		this.addEventListener(Events.ON_CHANGE, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				VentanaInterfazOperador winOperador = (VentanaInterfazOperador) event.getTarget();
				winOperador.elemMapa().actualizarPosicionMarkers();
			}
		});
	}

	// **************************************
	// ** ELEMENTOS
	// **************************************

	public MapaOperador elemMapa() {
		return (MapaOperador) this.getFellow(COMP__GMAP_OPERADOR);
	}

	// **************************************
	// ** MODELOS
	// **************************************

	public Collection<Viaje> getListaViajesEnCurso() {
		SortedSet<Viaje> lista = new TreeSet<Viaje>(Central.getInstance().getViajesEnCurso());
		return lista;
	}

	public Collection<Vehiculo> getListaVehiculosLibres() {
		SortedSet<Vehiculo> lista = new TreeSet<Vehiculo>(Central.getInstance().getVehiculosLibres());
		return lista;
	}

	public Collection<Viaje> getListaViajesPendientes() {
		SortedSet<Viaje> lista = new TreeSet<Viaje>(Central.getInstance().getViajesPendientes());
		return lista;
	}

	public Collection<Vehiculo> getListaVehiculosDesactivados() {
		SortedSet<Vehiculo> lista = new TreeSet<Vehiculo>(Central.getInstance()
				.getVehiculosDesactivados());
		return lista;
	}

	// **************************************
	// ** ACCIONES SOBRE LISTAS
	// **************************************

	public void abrirFinalizacionDeViaje(Menupopup popup) {
		AsignarLiberarVehiculo win = (AsignarLiberarVehiculo) Executions.createComponents(
				ZUL__LIBERAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatVehiculo().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirAsignacionPorVehiculoLibre(Menupopup popup) {
		AsignarLiberarVehiculo win = (AsignarLiberarVehiculo) Executions.createComponents(
				ZUL__ASIGNAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatVehiculo().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirAsignacionPorViaje(Menupopup popup) {
		AsignarLiberarVehiculo win = (AsignarLiberarVehiculo) Executions.createComponents(
				ZUL__ASIGNAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemIdViajePendiente().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirCancelacionViaje(Menupopup popup) {
		CancelarViajePendiente win = (CancelarViajePendiente) Executions.createComponents(
				ZUL__CANCELAR_VIAJE, null, null);
		agregarRefrescoAlCierre(win);
		win.elemIdViajePendiente().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirActivacionVehiculo(Menupopup popup) {
		ActivarDesactivarVehiculo win = (ActivarDesactivarVehiculo) Executions.createComponents(
				ZUL__ACTIVAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatVehiculo().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
	}

	public void abrirDesactivacionVehiculo(Menupopup popup) {
		ActivarDesactivarVehiculo win = (ActivarDesactivarVehiculo) Executions.createComponents(
				ZUL__DESACTIVAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatVehiculo().setValue(
				(String) popup.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP));
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
	 * "desactivar vehiculo", entonces debe refrescarse la interfaz del
	 * operador. El resultado, es que se actualizan las listas.
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

	// **************************************
	// ** HELPER PARA REFRESCAR... QUERIDO
	// **************************************

	public static void refrescarVentana(Component someElement) {
		Event event = new Event(Events.ON_CHANGE, someElement.getFellow(COMP__WIN_OPERADOR));
		Events.sendEvent(event);
	}

}
