package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

/**
 * @author Alejandro Supertramp
 */
public class LoteDeVehiculosDesactivados implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		add(l, 1234, "TAXI11", "Bauer", "Vapor XXX", "VAP 492", "El vehiculo de Mario Lemiaux", true);
		add(l, 1235, "TAXI12", "BMW", "Track XXX", "ASD 933", "El vehiculo de Don Nadie", true);
		add(l, 1236, "TAXI13", "Ford", "Vapor XXX", "DAS 743", "El vehiculo de Kiwiman", false);
		add(l, 1237, "TAXI14", "Audi", "Vapor XXX", "LKD 983", "El vehiculo de Vespa", false);
		add(l, 1238, "TAXI15", "Citroen", "Vapor XXX", "AID 015", "El vehiculo de Marito", false);
		add(l, 1239, "TAXI16", "Ferrari", "Vapor XXX", "DKJ 093", "El vehiculo del Chino Cirujano",
				false);
		add(l, 1240, "TAXI17", "GMC", "Vapor XXX", "PTR 243", "Internaron al loro", false);
		add(l, 1241, "TAXI18", "YeY", "Vapor XXX", "LKD 764", "El vehiculo de Alejandro Supertramp",
				false);
		return l;
	}

	@Override
	public void cargar() {
		for (ObjetoDeDominio t : getLote()) {
			Central.getInstance().addVehiculo((Vehiculo) t);
		}
	}

	private void add(List<ObjetoDeDominio> l, int idVehiculo, String trackerId, String marca,
			String modelo, String patente, String detalle, Boolean activado) {

		Vehiculo v = new Vehiculo();

		v.setIdVehiculo(idVehiculo);
		v.setIdTracker(trackerId);
		v.setMarca(marca);
		v.setModelo(modelo);
		v.setPatente(patente);
		v.setDetalle(detalle);
		v.setActivado(activado);

		l.add(v);
	}

}
