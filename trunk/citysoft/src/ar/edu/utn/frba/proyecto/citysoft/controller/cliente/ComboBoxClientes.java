package ar.edu.utn.frba.proyecto.citysoft.controller.cliente;

import java.util.Collection;
import java.util.TreeSet;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.zkoss.zul.Combobox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class ComboBoxClientes extends Combobox {

	/*
	public Collection<Cliente> getClientes() {
		TreeSet<Cliente> c = new TreeSet<Cliente>(Central.getInstance().getClientes());
		return c;
	}
	*/
	
	public Collection<String> getNombresUsuarios() {
		final TreeSet<String> c = new TreeSet<String>();
		Collection<Cliente> clientes = Central.getInstance().getClientes();
		CollectionUtils.forAllDo(clientes, new Closure() {
			@Override
			public void execute(Object arg0) {
				Cliente v = (Cliente) arg0;
				c.add(v.getNombreUsuario());
			}
		});
		return c;
	}
	
}
