package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcadorFactory;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityPolilinea;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Track;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

/**
 * Este objeto representa la relacion entre un viaje y los dibujos en un mapa
 * que se relacionen con dicho viaje. Sirve para dibujar un origen, destino, y
 * el vehiculo si es necesario
 */
public abstract class PloteableDesdeViaje extends ObjetoPlotteable {

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public ObjetoDeDominio getObjetoDelModelo() {
		return this.viaje;
	}

	@Override
	public List<CityMarcador> getMarcadores() {
		List<CityMarcador> l = new ArrayList<CityMarcador>();
		l.add(getMarcadorOrigen());
		l.add(getMarcadorDestino());
		return l;
	}

	@Override
	public List<CityPolilinea> getPolilineas() {
		List<CityPolilinea> l = new ArrayList<CityPolilinea>();
		return l;
	}

	@Override
	public void actualizarPlotting() {
		actualizarPlottingOrigenDestino();
	}

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	protected Viaje viaje;
	protected CityMarcador marcadorOrigen;
	protected CityMarcador marcadorDestino;
	protected CityMarcador marcadorAsignado;
	protected CityMarcador marcadorTransportando;
	protected CityPolilinea polilineaRuta;

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public PloteableDesdeViaje(Viaje v) {
		this.viaje = v;
	}

	// **************************************
	// ** EJECUCION
	// **************************************

	protected CityMarcador getMarcadorOrigen() {
		if (this.marcadorOrigen == null || this.marcadorOrigen.isDirty()) {
			this.marcadorOrigen = CityMarcadorFactory.buildMarcadorOrigen(this.viaje);
		}
		return this.marcadorOrigen;
	}

	protected CityMarcador getMarcadorDestino() {
		if (this.marcadorDestino == null || this.marcadorDestino.isDirty()) {
			this.marcadorDestino = CityMarcadorFactory.buildMarcadorDestino(this.viaje);
		}
		return this.marcadorDestino;
	}

	protected CityMarcador getMarcadorAsignado() {
		if (this.marcadorAsignado == null || this.marcadorAsignado.isDirty()) {
			this.marcadorAsignado = CityMarcadorFactory.buildMarcadorTransportando(this.viaje);
		}
		return this.marcadorAsignado;
	}

	protected CityMarcador getMarcadorTransportando() {
		if (this.marcadorTransportando == null || this.marcadorTransportando.isDirty()) {
			this.marcadorTransportando = CityMarcadorFactory.buildMarcadorTransportando(this.viaje);
		}
		return this.marcadorTransportando;
	}

	protected CityPolilinea getPolilineaRuta() {
		if (this.polilineaRuta == null || this.polilineaRuta.isDirty()) {
			this.polilineaRuta = new CityPolilinea();
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
	protected void actualizarPlottingOrigenDestino() {
		this.marcadorOrigen.setLat(this.viaje.getOrigenLatitud());
		this.marcadorOrigen.setLng(this.viaje.getOrigenLongitud());
		this.marcadorDestino.setLat(this.viaje.getDestinoLatitud());
		this.marcadorDestino.setLng(this.viaje.getDestinoLongitud());
	}

}
