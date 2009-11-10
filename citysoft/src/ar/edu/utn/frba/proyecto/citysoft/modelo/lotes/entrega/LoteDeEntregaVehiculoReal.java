package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;

public class LoteDeEntregaVehiculoReal implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		Conductor c;
		c = buildConductor("Quirce", "Nicolas", 30111222, "Quintino Bocayuva 615", "4911-1000");
		add(l, 10, "TAXI01", "Ford", "Ka", "NIC 666", "El vehiculo de Quaiers", c, true);
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
		return "Lote de vehiculo de nico para entrega final";
	}

}
