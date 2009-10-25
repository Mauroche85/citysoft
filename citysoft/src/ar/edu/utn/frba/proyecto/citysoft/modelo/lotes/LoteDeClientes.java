package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;

/**
 * @author Alejandro
 */
public class LoteDeClientes implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		add(l, "adelrio", 1156550, "Del Rio", "Alejo", 30887225, "Pumacahua 15", "4632-3396");
		add(l, "gdonnadie", 1137712, "Donnadie", "Gabriel", 28730690, "Juan Peña 15", "0800-hothandy");
		add(l, "abaldoza", 1151368, "Baldoza", "Ariel", 30979085, "Av Siempreviva 15", "0800-heisser");
		return l;
	}

	@Override
	public void cargar() {
		for (ObjetoDeDominio c : getLote()) {
			Central.getInstance().addCliente((Cliente) c);
		}
	}

	private void add(List<ObjetoDeDominio> l, String username, int idCliente, String apellido,
			String nombre, int dni, String direccion, String telefono) {
		Cliente cliente = new Cliente();
		cliente.setNombreUsuario(username);
		cliente.setPassword("soycalamardo");
		cliente.setIdCliente(idCliente);
		// Atributos de persona
		cliente.setApellido(apellido);
		cliente.setNombre(nombre);
		cliente.setDni(dni);
		cliente.setDireccion(direccion);
		cliente.setLocalidad("Capital Federal");
		cliente.setTelefono(telefono);

		l.add(cliente);
	}

}
