package ar.edu.utn.frba.proyecto.citysoft.controller.listadoVehiculos;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.abmVehiculo.VentanaAbmVehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class VentanaListadoVehiculos extends Window {

	private static final long serialVersionUID = 9103912640176445638L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public Collection<Vehiculo> getListaVehiculos() {
		SortedSet<Vehiculo> lista = new TreeSet<Vehiculo>(Central.getInstance().getVehiculos());
		return lista;
	}

	public void eliminar(int idVehiculo) {
		Vehiculo c = Central.getInstance().getVehiculoPorId(idVehiculo);
		try {  // si retorna 0x0010 es 'YES', 0x0020 es 'NO'
			 if(Messagebox.show("¿Confirma la baja?", "Mensaje de confirmación", Messagebox.YES | Messagebox.NO ,
			    Messagebox.QUESTION)==16)  
			    {Central.getInstance().getVehiculos().remove(c);}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}					
		this.refrescarTabla();
	}

	public void modificar(int idVehiculo) {
		Component componenteAbmVehiculo = Executions.createComponents("altaVehiculo.zul", null, null);
		VentanaAbmVehiculo win = (VentanaAbmVehiculo) componenteAbmVehiculo.getFellow("winAltaVehiculo");
		win.addEventListener(Events.ON_CLOSE, new OnCloseRefrescarTabla(this));
		win.abrirModificacion(idVehiculo);
	}

	public void refrescarTabla() {
		Event event = new Event(Events.ON_CHANGE, this.getFellow("grid"));
		Events.sendEvent(event);
	}

	// **************************************
	// ** INNER CLASS. ¿¿¿Servirá para extenderla hacia afuera???
	// **************************************

	private class OnCloseRefrescarTabla implements EventListener {
		private VentanaListadoVehiculos win;

		public OnCloseRefrescarTabla(VentanaListadoVehiculos win) {
			this.win = win;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			this.win.refrescarTabla();
		}
	}

}
