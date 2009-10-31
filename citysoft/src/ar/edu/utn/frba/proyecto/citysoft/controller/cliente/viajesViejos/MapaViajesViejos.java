package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.viajesViejos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.controller.cliente.follower.FollowerWindowUtils;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class MapaViajesViejos extends Gmaps implements ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = 4128949782922954635L;

	private Map<Vehiculo, Gmarker> vehiculosEnSeguimiento = new HashMap<Vehiculo, Gmarker>();

	// **************************************
	// ** ACCESSORS
	// **************************************

	public Map<Vehiculo, Gmarker> getVehiculosEnSeguimiento() {
		return this.vehiculosEnSeguimiento;
	}

	/**
	 * Para que la página pueda completar la lista de viajes en seguimiento con
	 * los vehiculos que tiene registrado este googlemap
	 */
	public List<Vehiculo> getVehiculosSet() {
		return new ArrayList<Vehiculo>(this.getVehiculosEnSeguimiento().keySet());
	}

	// **************************************
	// ** ACTUALIZAR PANTALLA
	// **************************************

	public void actualizarVista() {
		for (Vehiculo v : this.vehiculosEnSeguimiento.keySet()) {
			Gmarker gm = this.vehiculosEnSeguimiento.get(v);
			actualizarMarker(gm, v);
		}
	}

	private void actualizarMarker(Gmarker gm, Vehiculo v) {
		Coordenadas ultimasCoordenadas = v.getUltimoTrack().getCoordenadas();
		gm.setLat(ultimasCoordenadas.getLatitud());
		gm.setLng(ultimasCoordenadas.getLongitud());
	}

	// **************************************
	// ** AGREGAR Y QUITAR VEHICULOS SEGUIDOS
	// **************************************

	public void agregarGmark(Vehiculo v) {
		// 1. Creo y configuro el marker
		Gmarker gm = new Gmarker();
		gm.setId(GMARK_PREFIX + v.getPatente());
		gm.setIconImage(ConstantesGeneralesDeVentanas.IMAGES__VEHICULO_MARKER);
		gm.setDraggable(null);
		gm.setLat(v.getUltimoTrack().getCoordenadas().getLatitud());
		gm.setLng(v.getUltimoTrack().getCoordenadas().getLongitud());
		// 2. Agrego el marker a la lista
		this.vehiculosEnSeguimiento.put(v, gm);
		// 3. Renderizo el marker
		gm.setParent(this);
		// 4. Centro el mapa sobre el item recién ingresado
		Coordenadas ultimasCoordenadas = v.getUltimoTrack().getCoordenadas();
		this.setLat(ultimasCoordenadas.getLatitud());
		this.setLng(ultimasCoordenadas.getLongitud());
	}

	public void quitarGmark(String patente) {
		Vehiculo v = Central.getInstance().getVehiculoPorPatente(patente);
		Gmarker gm = this.vehiculosEnSeguimiento.remove(v);
		this.removeChild(gm);
	}

}
