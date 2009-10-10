package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

/**
 * @author Alejandro Supertramp
 */
public class LoteDeTaxisDesactivados implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> l = new ArrayList<ObjetoDeDominio>();
		add(l, "TAXI01", "Bauer", "Vapor XXX", "VAP 492", "El vehiculo de Mario Lemiaux");
		add(l, "TAXI02", "BMW", "Track XXX", "ASD 933", "El vehiculo de Don Nadie");
		add(l, "TAXI03", "Ford", "Vapor XXX", "DAS 743", "El vehiculo de Kiwiman");
		add(l, "TAXI04", "Audi", "Vapor XXX", "LKD 983", "El vehiculo de Vespa");
		add(l, "TAXI05", "Citroen", "Vapor XXX", "AID 015", "El vehiculo de Marito");
		add(l, "TAXI06", "Ferrari", "Vapor XXX", "DKJ 093", "El vehiculo del Chino Cirujano");
		add(l, "TAXI07", "GMC", "Vapor XXX", "PTR 243", "Internaron al loro");
		add(l, "TAXI08", "YeY", "Vapor XXX", "LKD 764", "El vehiculo de Alejandro Supertramp");
		return l;
	}

	@Override
	public void cargar() {
		for (ObjetoDeDominio t : getLote()) {
			CentralTaxis.getInstance().addTaxi((Taxi) t);
		}
	}

	private void add(List<ObjetoDeDominio> l, String trackerId, String marca, String modelo,
			String patente, String detalle) {

		Taxi taxi = new Taxi();

		taxi.setIdTracker(trackerId);
		taxi.setMarca(marca);
		taxi.setModelo(modelo);
		taxi.setPatente(patente);
		taxi.setDetalle(detalle);
		taxi.setActivado(false);

		l.add(taxi);
	}

}
