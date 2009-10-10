package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

public class VentanaInterfazOperador extends Window {

	// **************************************
	// ** EJECUCION
	// **************************************

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715263410797692543L;

	public Collection<Taxi> getListaVehiculosDesactivados() {
		SortedSet<Taxi> lista = new TreeSet<Taxi>(CentralTaxis.getInstance().getTaxisDesactivados());
		return lista;
	}
	
}
