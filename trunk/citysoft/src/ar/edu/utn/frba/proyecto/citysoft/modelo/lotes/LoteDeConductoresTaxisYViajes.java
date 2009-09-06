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
		// TODO completar con taxis de prueba
		return list;
	}

	public void cargar() {
		// **************************************
		// ** TAXI DEL CONDUCTOR JORGE URETA Y SU VIAJE
		// **************************************
		Conductor c = buildConductor("Ureta", "Jorge");
		Taxi t = buildTaxi(1, "TAXI01");
		Viaje v = buildViaje(1);
		c.setTaxi(t);
		t.addViaje(v);
		t.setViajeEnCurso(v);
		CentralTaxis.getInstance().addConductor(c);
		CentralTaxis.getInstance().addTaxi(t);
		CentralTaxis.getInstance().addViaje(v);

		// **************************************
		// ** TAXI DEL CONDUCTOR MARIO LEMIAUX Y SU VIAJE
		// **************************************
		c = buildConductor("Mario", "Lemiaux");
		t = buildTaxi(2, "TAXI02");
		v = buildViaje(2);
		c.setTaxi(t);
		t.addViaje(v);
		t.setViajeEnCurso(v);
		CentralTaxis.getInstance().addConductor(c);
		CentralTaxis.getInstance().addTaxi(t);
		CentralTaxis.getInstance().addViaje(v);
	}

	private Conductor buildConductor(String apellido, String nombre) {
		Conductor c = new Conductor();
		c.setApellido(apellido);
		c.setNombre(nombre);
		return c;
	}

	private Taxi buildTaxi(int idTaxi, String trackerId) {
		Taxi t = new Taxi();
		t.setIdTaxi(idTaxi);
		t.setIdTracker(trackerId);
		return t;
	}

	private Viaje buildViaje(int idViaje) {
		Viaje v = new Viaje();
		v.setIdViaje(idViaje);
		return v;
	}

}
