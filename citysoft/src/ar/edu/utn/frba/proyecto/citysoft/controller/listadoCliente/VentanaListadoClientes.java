package ar.edu.utn.frba.proyecto.citysoft.controller.listadoCliente;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class VentanaListadoClientes extends Window {

	private static final long serialVersionUID = -3401198084493337554L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public Collection<Cliente> getListaClientes() {
		SortedSet<Cliente> lista = new TreeSet<Cliente>(CentralTaxis.getInstance().getClientes());
		return lista;
	}

	public void delete(int legajo) {
		Cliente c = CentralTaxis.getInstance().getClientePorId(legajo);
		CentralTaxis.getInstance().getClientes().remove(c);
	}

}
