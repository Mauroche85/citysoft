package ar.edu.utn.frba.proyecto.citysoft.controller.listadoConductores;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.abmConductor.VentanaAbmConductor;
import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;

public class VentanaListadoConductores extends Window {

	private static final long serialVersionUID = 9103912640176445638L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public Collection<Conductor> getListaConductores() {
		SortedSet<Conductor> lista = new TreeSet<Conductor>(CentralTaxis.getInstance().getConductores());
		return lista;
	}

/*	public void eliminar(int legajo) {
		Conductor c = CentralTaxis.getInstance().getConductorPorId(legajo);
		CentralTaxis.getInstance().getConductores().remove(c);
		this.refrescarTabla();
	}*/

	public void eliminar(int legajo) {
		Conductor c = CentralTaxis.getInstance().getConductorPorId(legajo);
		CentralTaxis.getInstance().getConductores().remove(c);
		this.refrescarTabla();
	}
	
/*	Executions.createComponents("altaCliente.zul", null, null).abrirAlta();	
	*/
	public void modificar(int legajo) {
		Component componenteAbmConductor = Executions.createComponents("altaConductor.zul", null, null);
		VentanaAbmConductor win = (VentanaAbmConductor) componenteAbmConductor.getFellow("winAltaConductor");
		win.addEventListener(Events.ON_CLOSE, new OnCloseRefrescarTabla(this));
		win.abrirModificacion(legajo);
	}

	public void refrescarTabla() {
		Event event = new Event(Events.ON_CHANGE, this.getFellow("grid"));
		Events.sendEvent(event);
	}

	// **************************************
	// ** INNER CLASS. ���Servir� para extenderla hacia afuera???
	// **************************************

	private class OnCloseRefrescarTabla implements EventListener {
		private VentanaListadoConductores win;

		public OnCloseRefrescarTabla(VentanaListadoConductores win) {
			this.win = win;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			this.win.refrescarTabla();
		}
	}

}
