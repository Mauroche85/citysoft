package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Listbox;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityVentanaConMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteable;
import ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador.AsignarLiberarVehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class VentanaControlDeViajes extends CityVentanaConMapa implements ConstantesControlDeViajes {

	private static final long serialVersionUID = -5296048887254088749L;

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	private static final String LISTA_VIAJES_TRANSPORTANDO = "listaViajesViejos";
	private static final String LISTA_VIAJES_ASIGNADOS = "listaViajesAsignados";
	private static final String LISTA_VIAJES_PENDIENTES = "listaViajesPendientes";
	private static final String LISTA_VEHICULOS_LIBRES = "listaVehiculosLibres";
	private static final String LISTA_VEHICULOS_DESACTIVADOS = "listaVehiculosDesactivados";

	// **************************************
	// ** INTERFAZ
	// **************************************

	public String getIdMapa() {
		return COMP__GMAP_CONTROL_VIAJES;
	}

	public List<ObjetoDeDominio> obtenerListaFuenteDe(String nombreLista) {
		if (nombreLista.equals(LISTA_VIAJES_TRANSPORTANDO))
			return (List<ObjetoDeDominio>) getViajesBajoTransporte();
		else if (nombreLista.equals(LISTA_VIAJES_ASIGNADOS))
			return (List<ObjetoDeDominio>) getViajesAsignados();
		else if (nombreLista.equals(LISTA_VIAJES_PENDIENTES))
			return (List<ObjetoDeDominio>) getViajesPendientes();
		else if (nombreLista.equals(LISTA_VEHICULOS_LIBRES))
			return (List<ObjetoDeDominio>) getVehiculosLibres();
		else if (nombreLista.equals(LISTA_VEHICULOS_DESACTIVADOS))
			return (List<ObjetoDeDominio>) getVehiculosDesactivados();
		else
			throw new RuntimeException("No existe lista fuente para: " + nombreLista);
	}

	// **************************************
	// ** HELPERS
	// **************************************

	public List<ObjetoDeDominio> getViajesBajoTransporte() {
		List<ObjetoDeDominio> lista = new ArrayList<ObjetoDeDominio>(Central.getInstance()
				.getViajesBajoTransporte());
		return lista;
	}

	public List<ObjetoDeDominio> getViajesAsignados() {
		List<ObjetoDeDominio> lista = new ArrayList<ObjetoDeDominio>(Central.getInstance()
				.getViajesAsignados());
		return lista;
	}

	public List<ObjetoDeDominio> getViajesPendientes() {
		List<ObjetoDeDominio> lista = new ArrayList<ObjetoDeDominio>(Central.getInstance()
				.getViajesPendientes());
		return lista;
	}

	public List<ObjetoDeDominio> getVehiculosLibres() {
		List<ObjetoDeDominio> lista = new ArrayList<ObjetoDeDominio>(Central.getInstance()
				.getVehiculosLibres());
		return lista;
	}

	public List<ObjetoDeDominio> getVehiculosDesactivados() {
		List<ObjetoDeDominio> lista = new ArrayList<ObjetoDeDominio>(Central.getInstance()
				.getVehiculosDesactivados());
		return lista;
	}

	// **************************************
	// ** MODELOS ZUL
	// **************************************

	public Set<ObjetoPlotteable> getModeloViajesTransportando() {
		return obtenerLista(LISTA_VIAJES_TRANSPORTANDO);
	}

	public Set<ObjetoPlotteable> getModeloViajesAsignados() {
		return obtenerLista(LISTA_VIAJES_ASIGNADOS);
	}

	public Set<ObjetoPlotteable> getModeloViajesPendientes() {
		return obtenerLista(LISTA_VIAJES_PENDIENTES);
	}

	public Set<ObjetoPlotteable> getModeloVehiculosLibres() {
		return obtenerLista(LISTA_VEHICULOS_LIBRES);
	}

	public Set<ObjetoPlotteable> getModeloVehiculosDesactivados() {
		return obtenerLista(LISTA_VEHICULOS_DESACTIVADOS);
	}

	// **************************************
	// ** ACTUALIZACION DE PAGINA:
	// ** 1- Plotting
	// ** 2- Listas
	// **************************************

	public void actualizarPagina() {
		actualizarListas();
		actualizarPlottings();
	}

	private void actualizarListas() {
		sincronizarListas();
		// Set<String> cambios = getListasQueCambiaron();
		// if (cambios.contains(LISTA_VIAJES_TRANSPORTANDO)) {
		// dispararCambiosEnLista(COMP__LISTA_VIAJES_TRANSPORTANDO);
		// cambios.remove(LISTA_VIAJES_TRANSPORTANDO);
		// }
		// if (cambios.contains(LISTA_VIAJES_ASIGNADOS)) {
		// dispararCambiosEnLista(COMP__LISTA_VIAJES_ASIGNADOS);
		// cambios.remove(LISTA_VIAJES_ASIGNADOS);
		// }
		// if (cambios.contains(LISTA_VIAJES_PENDIENTES)) {
		// dispararCambiosEnLista(COMP__LISTA_VIAJES_PENDIENTES);
		// cambios.remove(LISTA_VIAJES_PENDIENTES);
		// }
		// if (cambios.contains(LISTA_VEHICULOS_LIBRES)) {
		// dispararCambiosEnLista(COMP__LISTA_VEHICULOS_LIBRES);
		// cambios.remove(LISTA_VEHICULOS_LIBRES);
		// }
		// if (cambios.contains(LISTA_VEHICULOS_DESACTIVADOS)) {
		// dispararCambiosEnLista(COMP__LISTA_VEHICULOS_DESACTIVADOS);
		// cambios.remove(LISTA_VEHICULOS_DESACTIVADOS);
		// }
		dispararCambiosEnLista(COMP__LISTA_VIAJES_TRANSPORTANDO);
		dispararCambiosEnLista(COMP__LISTA_VIAJES_ASIGNADOS);
		dispararCambiosEnLista(COMP__LISTA_VIAJES_PENDIENTES);
		dispararCambiosEnLista(COMP__LISTA_VEHICULOS_LIBRES);
		dispararCambiosEnLista(COMP__LISTA_VEHICULOS_DESACTIVADOS);
	}

	/**
	 * Dispara el evento onChange en la lista de vehiculos especificada, para
	 * que se refresque. Este método debe dispararse desde los procesos que
	 * generan un seguimiento o lo eliminan.
	 */
	private void dispararCambiosEnLista(String nombreComponente) {
		Listbox listbox = (Listbox) getFellow(nombreComponente);
		Events.sendEvent(new Event(Events.ON_CHANGE, listbox));
	}

	// **************************************
	// ** ACCIONES ZUL
	// **************************************

	public void abrirNuevoViaje() throws InterruptedException {
		Window win = (Window) Executions.createComponents(ZUL__NUEVO_VIAJE, null, null);
		win.setMode("modal");
		win.setWidth("75%");
		win.setHeight("90%");
		win.setClosable(true);
		agregarRefrescoAlCierre(win);
	}

	public void finalizarViaje() {
		Viaje v = getViajeQueAbrioElPopup();
		v.finalizar();
		// Cuando se modifica alguna entidad, hay que re-agregarla en la central
		Central.getInstance().addViaje(v);
		actualizarListas();
	}

	public void comenzarViaje() {
		Viaje v = getViajeQueAbrioElPopup();
		v.comenzar();
		// Cuando se modifica alguna entidad, hay que re-agregarla en la central
		Central.getInstance().addViaje(v);
		actualizarListas();
	}

	public void liberarVehiculo() {
		Viaje v = getViajeQueAbrioElPopup();
		v.liberar();
		// Cuando se modifica alguna entidad, hay que re-agregarla en la central
		Central.getInstance().addViaje(v);
		actualizarListas();
	}

	public void cancelarViajePendiente() {
		Viaje v = getViajeQueAbrioElPopup();
		v.cancelar();
		// Cuando se modifica alguna entidad, hay que re-agregarla en la central
		Central.getInstance().addViaje(v);
		actualizarListas();
	}

	public void abrirAsignacionPorViaje() {
		Viaje v = getViajeQueAbrioElPopup();
		AsignarLiberarVehiculo win = (AsignarLiberarVehiculo) Executions.createComponents(
				ZUL__ASIGNAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemIdViajePendiente().setValue(String.valueOf(v.getIdViaje()));
	}

	public void abrirAsignacionPorVehiculoLibre() {
		Vehiculo v = getVehiculoQueAbrioElPopup();
		AsignarLiberarVehiculo win = (AsignarLiberarVehiculo) Executions.createComponents(
				ZUL__ASIGNAR_VEHICULO, null, null);
		agregarRefrescoAlCierre(win);
		win.elemPatVehiculo().setValue(v.getPatente());
	}

	public void activarVehiculo(boolean activado) {
		Vehiculo v = getVehiculoQueAbrioElPopup();
		v.setActivado(activado);
		// Cuando se modifica alguna entidad, hay que re-agregarla en la central
		Central.getInstance().addVehiculo(v);
		actualizarListas();
	}

	// **************************************
	// ** ACCIONES - HELPER
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
		final VentanaControlDeViajes w = this;
		win.addEventListener(Events.ON_CLOSE, new EventListener() {
			public void onEvent(Event event) throws Exception {
				w.actualizarListas();
			}
		});
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private Viaje getViajeQueAbrioElPopup() {
		return (Viaje) quienAbrioElPopup().getObjetoDelModelo();
	}

	private Vehiculo getVehiculoQueAbrioElPopup() {
		return (Vehiculo) quienAbrioElPopup().getObjetoDelModelo();
	}

	private ObjetoPlotteable quienAbrioElPopup() {
		Desktop desktop = Executions.getCurrent().getDesktop();
		ObjetoPlotteable quienAbrio = (ObjetoPlotteable) desktop
				.getAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP);
		desktop.removeAttribute(CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP);
		return quienAbrio;
	}

}
