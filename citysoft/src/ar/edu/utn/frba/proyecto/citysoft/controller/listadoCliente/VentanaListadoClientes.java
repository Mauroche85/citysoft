package ar.edu.utn.frba.proyecto.citysoft.controller.listadoCliente;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.abmCliente.VentanaAbmCliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class VentanaListadoClientes extends Window {

	private static final long serialVersionUID = 9103912640176445638L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public Collection<Cliente> getListaClientes() {
		SortedSet<Cliente> lista = new TreeSet<Cliente>(Central.getInstance().getClientes());
		return lista;
	}

	public void eliminar(int legajo) {
		Cliente c = Central.getInstance().getClientePorId(legajo);
		Central.getInstance().getClientes().remove(c);
		this.refrescarTabla();
	}

	public void modificar(int legajo) {
		Component componenteAbmCliente = Executions.createComponents("altaCliente.zul", null, null);
		VentanaAbmCliente win = (VentanaAbmCliente) componenteAbmCliente.getFellow("winAltaCliente");
		win.addEventListener(Events.ON_CLOSE, new OnCloseRefrescarTabla(this));
		win.abrirModificacion(legajo);
	}

	public void refrescarTabla() {
		Event event = new Event(Events.ON_CHANGE, this.getFellow("grid"));
		Events.sendEvent(event);
	}

	// **************************************
	// ** INNER CLASS. ¿¿¿Servirá para extenderla hacia afuera???
	// **************************************

	private class OnCloseRefrescarTabla implements EventListener {
		private VentanaListadoClientes win;

		public OnCloseRefrescarTabla(VentanaListadoClientes win) {
			this.win = win;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			this.win.refrescarTabla();
		}
	}

}
