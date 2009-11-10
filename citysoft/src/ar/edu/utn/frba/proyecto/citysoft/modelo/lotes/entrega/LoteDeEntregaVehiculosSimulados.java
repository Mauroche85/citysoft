package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;

public class LoteDeEntregaVehiculosSimulados implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		Conductor c1, c2, c3;
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		// Creamos los conductores
		c1 = buildConductor("Marimón", "Guillermo", 900, "Medrano 950", "0800-UTN");
		c2 = buildConductor("Marino", "Gastón", 901, "Medrano 950", "0800-UTN");
		c3 = buildConductor("Mariosa", "Gonzalo", 902, "Medrano 950", "0800-UTN");
		// Creamos los vehiculos asociados
		add(l, 11, "SIM1", "Renault", "21", "SIM 001", "El vehiculo 1 de simulacion", c1, false);
		add(l, 12, "SIM2", "Ford", "Escort", "SIM 002", "El vehiculo 2 de simulacion", c2, false);
		add(l, 13, "SIM3", "Chevrolet", "Corsa", "SIM 003", "El vehiculo 3 de simulacion", c3, false);
		return l;
	}

	@Override
	public void cargar() {
		for (ObjetoDeDominio t : getLote()) {
			Central.getInstance().addVehiculo((Vehiculo) t);
		}
	}

	// **************************************
	// ** Helpers
	// **************************************

	private void add(List<ObjetoDeDominio> l, int idVehiculo, String trackerId, String marca,
			String modelo, String patente, String detalle, Conductor c, Boolean activado) {
		Vehiculo v = new Vehiculo(idVehiculo);
		v.setIdTracker(trackerId);
		v.setMarca(marca);
		v.setModelo(modelo);
		v.setPatente(patente);
		v.setDetalle(detalle);
		v.setConductor(c);
		v.setActivado(activado);
		l.add(v);
	}

	private Conductor buildConductor(String apellido, String nombre, int dni, String dir, String tel) {
		Conductor c = new Conductor();
		c.setApellido(apellido);
		c.setNombre(nombre);
		c.setDni(dni);
		c.setDireccion(dir);
		c.setLocalidad("Capital Federal");
		c.setTelefono(tel);
		return c;
	}

	// **************************************
	// ** Object
	// **************************************

	public String toString() {
		return "Lote de vehiculos simulados para entrega final";
	}

}
