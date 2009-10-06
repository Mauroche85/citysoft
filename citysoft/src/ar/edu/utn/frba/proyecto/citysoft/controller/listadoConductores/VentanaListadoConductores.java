package ar.edu.utn.frba.proyecto.citysoft.controller.listadoConductores;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;

public class VentanaListadoConductores extends Window {

	public Collection<Conductor> getListaConductores() {
		return new TreeSet<Conductor>(CentralTaxis.getInstance().getConductores());
	}

}
