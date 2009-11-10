package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;

/**
 * @author Alejandro
 */
public class LoteDeEntregaClientes implements Lote {

	/**
	 * A Tomassino le creamos un viaje pendiente para la prueba, y un viaje
	 * viejo para ver el historial de viajes.
	 * 
	 * @see LoteDeEntregaViajes
	 */
	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		add(l, "ctomassino", "Tomassino", "Carlos", 28000000, "Medrano 950", "0800-UTN");
		add(l, "mjsuasnabar", "Suasnábar", "María Julia", 28000001, "Medrano 950", "0800-UTN");
		add(l, "mmeoniz", "Meoniz", "Marcela", 28000002, "Medrano 950", "0800-UTN");
		return l;
	}

	@Override
	public void cargar() {
		for (ObjetoDeDominio c : getLote()) {
			Central.getInstance().addCliente((Cliente) c);
		}
	}

	private void add(List<ObjetoDeDominio> l, String username, String apellido, String nombre,
			int dni, String direccion, String telefono) {
		Cliente cliente = new Cliente();
		cliente.setNombreUsuario(username);
		cliente.setPassword("citysoft");
		// Atributos de persona
		cliente.setApellido(apellido);
		cliente.setNombre(nombre);
		cliente.setDni(dni);
		cliente.setDireccion(direccion);
		cliente.setLocalidad("Capital Federal");
		cliente.setTelefono(telefono);

		l.add(cliente);
	}

	// **************************************
	// ** Object
	// **************************************

	public String toString() {
		return "Lote de clientes para entrega final";
	}

}
