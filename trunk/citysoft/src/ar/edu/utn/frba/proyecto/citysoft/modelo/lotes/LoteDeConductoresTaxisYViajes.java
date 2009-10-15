package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * @author Alejandro
 */
public class LoteDeConductoresTaxisYViajes implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> list = new ArrayList<ObjetoDeDominio>();
		Conductor c;

		// **************************************
		// ** TAXI DEL CONDUCTOR JORGE URETTA Y SU VIAJE
		// **************************************
		c = buildConductor(111, "Uretta", "Jorge", 10111000, "Quintino Bocayuva 615", "4911-1000");
		Taxi t = buildTaxi("TAXI01", "Renault", "19", "JUT 111", "El vehiculo de Jorge Uretta");
		Viaje v = buildViaje(1);
		c.setTaxi(t);
		v.asignar(t);
		list.add(c);

		// **************************************
		// ** TAXI DEL CONDUCTOR MARIO LEMIAUX Y SU VIAJE
		// **************************************
		c = buildConductor(222, "Mario", "Lemiaux", 10222000, "Slapshot 3", "4922-2000");
		t = buildTaxi("TAXI02", "Bauer", "Vapor XXX", "VAP 030", "El vehiculo de Mario Lemiaux");
		v = buildViaje(2);
		c.setTaxi(t);
		v.asignar(t);
		CentralTaxis.getInstance().addConductor(c);
		CentralTaxis.getInstance().addTaxi(t);
		CentralTaxis.getInstance().addViaje(v);
		list.add(c);

		// **************************************
		// ** TAXI DEL CONDUCTOR GUILLERMO MARIMON Y SU VIAJE
		// **************************************
		c = buildConductor(333, "Marimon", "Guillermo", 10333000, "Bañeros 3", "4933-3000");
		t = buildTaxi("TAXI03", "Fiat", "128", "GUI 333", "El vehiculo de Guillermo Marimon");
		v = buildViaje(3);
		c.setTaxi(t);
		v.asignar(t);
		CentralTaxis.getInstance().addConductor(c);
		CentralTaxis.getInstance().addTaxi(t);
		CentralTaxis.getInstance().addViaje(v);
		list.add(c);

		return list;
	}

	public void cargar() {
		for (ObjetoDeDominio objetoDominio : getLote()) {
			Conductor c = (Conductor) objetoDominio;
			CentralTaxis.getInstance().addConductor(c);
			CentralTaxis.getInstance().addTaxi(c.getTaxi());
			CentralTaxis.getInstance().addViaje(c.getTaxi().getViajeEnCurso());
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

	private Taxi buildTaxi(String trackerId, String marca, String modelo, String patente,
			String detalle) {
		Taxi t = new Taxi();
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
		v.setIdViaje(idViaje);
		return v;
	}

}
