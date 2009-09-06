package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;

/**
 * @author Alejandro
 */
public class LoteDeClientes implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> list = new ArrayList<ObjetoDeDominio>();
		// TODO completar con taxis de prueba
		return list;
	}

	public void cargar() {
		Cliente c = buildCliente("Del Rio", "Alejo", "adelrio");
		CentralTaxis.getInstance().addCliente(c);
	}

	private Cliente buildCliente(String apellido, String nombre, String username) {
		Cliente cliente = new Cliente();
		cliente.setApellido(apellido);
		cliente.setNombre(nombre);
		cliente.setNombreUsuario(username);
		return cliente;
	}

}
