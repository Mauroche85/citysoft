package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.gmaps.Gpolyline;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcadorFactory;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Track;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public class PloteableDesdeViaje extends ObjetoPlotteable {

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public Object getObjetoDelModelo() {
		return this.viaje;
	}

	@Override
	public void mostrarMarcadores(CityMapa nuevoMapa) {
		// Si tengo un mapa viejo, quito los markers
		if (this.tengoMapa()) {
			this.marcadorOrigen.detach();
			this.marcadorDestino.detach();
			if (this.viaje.estaTransportando())
				this.marcadorTransportando.detach();
			else if (this.viaje.fueCompletado()) {
				this.polilineaRuta.detach();
			}
		}
		// Si no existen los marcadores, se crean solos
		getMarcadorOrigen().setParent(nuevoMapa);
		getMarcadorDestino().setParent(nuevoMapa);
		if (this.viaje.estaTransportando())
			getMarcadorTransportando().setParent(nuevoMapa);
		else if (this.viaje.fueCompletado()) {
			getPolilinea().setParent(nuevoMapa);
		}
		// Ahora si... declaramos el nuevo mapa!
		this.mapa = nuevoMapa;
	}

	/**
	 * La interfaz nos dice bien clarito que debemos perder el rastro de los
	 * marcadores
	 */
	@Override
	public void quitarMarcadoresDelMapa() {
		this.marcadorOrigen.detach();
		this.marcadorDestino.detach();
		if (this.viaje.estaTransportando())
			this.marcadorTransportando.detach();
		else if (this.viaje.fueCompletado()) {
			this.polilineaRuta.detach();
		}
		// Perdemos las referencias a lo que creiamos era importante
		this.marcadorOrigen = null;
		this.marcadorDestino = null;
		if (this.viaje.estaTransportando())
			this.marcadorTransportando = null;
		else if (this.viaje.fueCompletado()) {
			this.polilineaRuta = null;
		}
		// Chau mapa
		this.mapa = null;
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = new ArrayList<CityMarcador>();
		l.add(this.marcadorOrigen);
		l.add(this.marcadorDestino);
		l.add(this.marcadorTransportando);
		return l;
	}

	@Override
	public void actualizarPlotting() {
		actualizarPlottingOrigenDestino();
		if (this.viaje.estaTransportando())
			actualizarPlottingTransportando();
	}

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	private CityMapa mapa;
	private Viaje viaje;
	private CityMarcador marcadorOrigen;
	private CityMarcador marcadorDestino;
	private CityMarcador marcadorTransportando;
	private Gpolyline polilineaRuta;

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViaje(Viaje v) {
		this.viaje = v;
	}

	// **************************************
	// ** EJECUCION
	// **************************************

	/**
	 * O tenemos el conjunto correspondiente de marcadores y el mapa, o no
	 * tenemos nada!!!
	 */
	private boolean tengoMapa() {
		if (this.mapa != null) {
			validarQueExistenLosMarcadoresYElMapa();
			return true;
		}
		return false;
	}

	private CityMarcador getMarcadorOrigen() {
		if (this.marcadorOrigen == null) {
			this.marcadorOrigen = CityMarcadorFactory.buildMarcadorOrigen(this.viaje);
		}
		return this.marcadorOrigen;
	}

	private CityMarcador getMarcadorDestino() {
		if (this.marcadorDestino == null) {
			this.marcadorDestino = CityMarcadorFactory.buildMarcadorDestino(this.viaje);
		}
		return this.marcadorDestino;
	}

	private CityMarcador getMarcadorTransportando() {
		if (this.marcadorTransportando == null) {
			this.marcadorTransportando = CityMarcadorFactory.buildMarcadorTransportando(this.viaje);
		}
		return this.marcadorTransportando;
	}

	private Gpolyline getPolilinea() {
		if (this.polilineaRuta == null) {
			this.polilineaRuta = new Gpolyline();
			this.polilineaRuta.setOpacity(50);
			this.polilineaRuta.setColor("#0000FF");
			for (Track track : this.viaje.getTracksDelViaje()) {
				Coordenadas c = track.getCoordenadas();
				this.polilineaRuta.addPoint(c.getLatitud(), c.getLongitud(), 3);
			}
		}
		return this.polilineaRuta;
	}

	// **************************************
	// ** HELPERS
	// **************************************

	/**
	 * La pregunta es... ¿pueden cambiar las coordenadas de origen y destino?
	 * Bueno, si bien en principio diriamos que no, el operador pudo equivocarse
	 * al tomar los datos o el cliente arrepentirse y rectificar el destino del
	 * viaje, por ejemplo
	 */
	private void actualizarPlottingOrigenDestino() {
		this.marcadorOrigen.setLat(this.viaje.getOrigenLatitud());
		this.marcadorOrigen.setLng(this.viaje.getOrigenLongitud());
		this.marcadorDestino.setLat(this.viaje.getDestinoLatitud());
		this.marcadorDestino.setLng(this.viaje.getDestinoLongitud());
	}

	private void actualizarPlottingTransportando() {
		this.marcadorTransportando.setLat(this.viaje.getVehiculo().getLat());
		this.marcadorTransportando.setLng(this.viaje.getVehiculo().getLng());
	}

	/**
	 * O tenemos el conjunto correspondiente de marcadores y el mapa, o no
	 * tenemos nada!!! Con esto le ponemos la firma. Sirve para asegurarse de
	 * que no ocurra ninguna situacion anomala
	 */
	private void validarQueExistenLosMarcadoresYElMapa() {
		if (this.mapa == null) {
			throw new RuntimeException("El objeto ploteable correspondiente al viaje"
					+ " carece de mapa");
		} else if (this.marcadorOrigen == null || this.marcadorDestino == null) {
			throw new RuntimeException("El objeto ploteable correspondiente al viaje"
					+ " carece del marcador de origen o destino");
		} else if (this.viaje.estaTransportando() && this.marcadorTransportando == null) {
			throw new RuntimeException(
					"El objeto ploteable correspondiente al viaje carece del marcador"
							+ " de transporte");
		}
	}

}
