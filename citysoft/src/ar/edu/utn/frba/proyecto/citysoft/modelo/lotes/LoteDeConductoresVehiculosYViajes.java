package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * @author Alejandro
 */
public class LoteDeConductoresVehiculosYViajes implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> list = new ArrayList<ObjetoDeDominio>();
		Conductor c;

		// **************************************
		// ** VEHICULO DEL CONDUCTOR JORGE URETTA Y SU VIAJE
		// **************************************
		c = buildConductor(111, "Uretta", "Jorge", 10111000, "Quintino Bocayuva 615", "4911-1000");
		Vehiculo t = buildVehiculo("TAXI01", "Renault", "19", "JUT 111",
				"El vehiculo de Jorge Uretta");
		Viaje v = buildViaje(1);
		c.setVehiculo(t);
		v.asignar(t);
		list.add(c);

		// **************************************
		// ** VEHICULO DEL CONDUCTOR MARIO LEMIAUX Y SU VIAJE
		// **************************************
		c = buildConductor(222, "Mario", "Lemiaux", 10222000, "Slapshot 3", "4922-2000");
		t = buildVehiculo("TAXI02", "Bauer", "Vapor XXX", "VAP 030", "El vehiculo de Mario Lemiaux");
		v = buildViaje(2);
		c.setVehiculo(t);
		v.asignar(t);
		list.add(c);

		// **************************************
		// ** VEHICULO DEL CONDUCTOR GUILLERMO MARIMON Y SU VIAJE
		// **************************************
		c = buildConductor(333, "Marimon", "Guillermo", 10333000, "Bañeros 3", "4933-3000");
		t = buildVehiculo("TAXI03", "Fiat", "128", "GUI 333", "El vehiculo de Guillermo Marimon");
		v = buildViaje(3);
		c.setVehiculo(t);
		v.asignar(t);
		list.add(c);

		return list;
	}

	public void cargar() {
		for (ObjetoDeDominio objetoDominio : getLote()) {
			Conductor c = (Conductor) objetoDominio;
			Central.getInstance().addConductor(c);
			Central.getInstance().addVehiculo(c.getVehiculo());
			Central.getInstance().addViaje(c.getVehiculo().getViajeEnCurso());
		}
	}

	// **************************************
	// ** Helpers
	// **************************************

	private Conductor buildConductor(int idConductor, String apellido, String nombre, int dni,
			String dir, String tel) {
		Conductor c = new Conductor();
		c.setIdConductor(idConductor);
		c.setApellido(apellido);
		c.setNombre(nombre);
		c.setDni(dni);
		c.setDireccion(dir);
		c.setLocalidad("Capital Federal");
		c.setTelefono(tel);
		return c;
	}

	private Vehiculo buildVehiculo(String trackerId, String marca, String modelo, String patente,
			String detalle) {
		Vehiculo t = new Vehiculo();
		t.setPatente(patente);
		t.setIdTracker(trackerId);
		t.setMarca(marca);
		t.setModelo(modelo);
		t.setPatente(patente);
		t.setDetalle(detalle);
		t.setActivado(true);
		return t;
	}

	private Viaje buildViaje(int idViaje) {
		Viaje v = new Viaje();
		v.setCliente(findCliente());
		v.setIdViaje(idViaje);
		return v;
	}

	private Cliente findCliente() {
		return Central.getInstance().getCliente("adelrio");
	}

}
