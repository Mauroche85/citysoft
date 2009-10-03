package ar.edu.utn.frba.proyecto.citysoft.controller.listadoCliente;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class VentanaListadoClientes extends Window {

	public Collection<Cliente> getListaClientes() {
		SortedSet<Cliente> lista = new TreeSet<Cliente>(CentralTaxis
				.getInstance().getClientes());
		return lista;
	}

}
