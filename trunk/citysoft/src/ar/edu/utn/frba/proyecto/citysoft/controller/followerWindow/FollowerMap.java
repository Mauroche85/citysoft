package ar.edu.utn.frba.proyecto.citysoft.controller.followerWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

public class FollowerMap extends Gmaps implements FollowerWindowComponents {

	private static final long serialVersionUID = 4128949782922954635L;

	private Map<Taxi, Gmarker> taxisEnSeguimiento = new HashMap<Taxi, Gmarker>();

	// **************************************
	// ** ACCESSORS
	// **************************************

	public Map<Taxi, Gmarker> getTaxisEnSeguimiento() {
		return this.taxisEnSeguimiento;
	}

	/**
	 * Para que la página pueda completar la lista de viajes en seguimiento con
	 * los taxis que tiene registrado este googlemap
	 */
	public List<Taxi> getTaxisSet() {
		return new ArrayList<Taxi>(this.getTaxisEnSeguimiento().keySet());
	}

	// **************************************
	// ** ACTUALIZAR PANTALLA
	// **************************************

	public void actualizarVista() {
		for (Taxi taxi : this.taxisEnSeguimiento.keySet()) {
			Gmarker gm = this.taxisEnSeguimiento.get(taxi);
			if (taxi.getIdTaxi() % 2 == 0)
				gm.setIconImage("images/taxiOcupadoMarker.png");
			else
				gm.setIconImage("images/taxiMarker.png");
			actualizarMarker(gm, taxi);
		}
	}

	private void actualizarMarker(Gmarker gm, Taxi taxi) {
		Coordenadas ultimasCoordenadas = taxi.getUltimoTrack().getCoordenadas();
		gm.setLat(ultimasCoordenadas.getLatitud());
		gm.setLng(ultimasCoordenadas.getLongitud());
		// **************************************
		// ** Centramos el mapa sobre el elemento si así se requiere
		// **************************************
		if (FollowerWindowUtils.seguimientoCentradoSobreTaxi(taxi, this)) {
			this.setLat(ultimasCoordenadas.getLatitud());
			this.setLng(ultimasCoordenadas.getLongitud());
		}
	}

	// **************************************
	// ** AGREGAR Y QUITAR TAXIS SEGUIDOS
	// **************************************

	public void agregarGmark(Taxi taxi) {
		// 1. Creo y configuro el marker
		Gmarker gm = new Gmarker();
		gm.setId(GMARK_PREFIX + taxi.getIdTaxi());
		gm.setIconImage("images/taxiMarker.png");
		gm.setDraggable(null);
		gm.setLat(taxi.getUltimoTrack().getCoordenadas().getLatitud());
		gm.setLng(taxi.getUltimoTrack().getCoordenadas().getLongitud());
		// 2. Agrego el marker a la lista
		this.taxisEnSeguimiento.put(taxi, gm);
		// 3. Renderizo el marker
		gm.setParent(this);
		// 4. Centro el mapa sobre el item recién ingresado
		Coordenadas ultimasCoordenadas = taxi.getUltimoTrack().getCoordenadas();
		this.setLat(ultimasCoordenadas.getLatitud());
		this.setLng(ultimasCoordenadas.getLongitud());
	}

	public void quitarGmark(int idTaxi) {
		Taxi taxi = CentralTaxis.getInstance().getTaxi(idTaxi);
		Gmarker gm = this.taxisEnSeguimiento.remove(taxi);
		this.removeChild(gm);
	}

}
