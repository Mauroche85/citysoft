package ar.edu.utn.frba.proyecto.citysoft.controller.bienvenidaCliente;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;
import org.zkoss.zul.api.Timebox;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

import com.blogspot.unserializableone.GAddress;
import com.blogspot.unserializableone.GCoder;

public class SolicitudNuevoViaje extends Window implements ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = 691719919215219297L;

	// **************************************
	// ** Constantes
	// **************************************

	private static final String GMAP_VIAJE = "gmapViaje";

	Gmarker gm = new Gmarker();
	Gmarker gmDestino = new Gmarker();

	public void previewViaje() {
		Textbox txtOrigenCalle = (Textbox) this.getFellow("stOrigenCalle");
		Textbox txtOrigenAltura = (Textbox) this.getFellow("stOrigenAltura");
		Textbox txtOrigenProvincia = (Textbox) this.getFellow("stOrigenProvincia");
		Textbox txtOrigenPisoDepto = (Textbox) this.getFellow("stOrigenPisoDepto");
		Textbox txtDestinoCalle = (Textbox) this.getFellow("stDestinoCalle");
		Textbox txtDestinoAltura = (Textbox) this.getFellow("stDestinoAltura");
		Textbox txtDestinoProvincia = (Textbox) this.getFellow("stDestinoProvincia");
		Textbox txtDestinoPisoDepto = (Textbox) this.getFellow("stDestinoPisoDepto");

		// **************************************
		// ** Busco cordenadas origen
		// **************************************
		GAddress gaOrigen = new GAddress();
		String gOrigen = txtOrigenCalle.getValue() + " " + txtOrigenAltura.getValue() + ", "
				+ txtOrigenProvincia.getValue() + ", Argentina";
		gaOrigen = GCoder.geocode(gOrigen);
		double origenLatitud = gaOrigen.getLat();
		double origenLongitud = gaOrigen.getLng();
		System.out.println("Coord origen: " + origenLatitud + " " + origenLongitud);

		// **************************************
		// ** Busco cordenadas destino
		// **************************************
		GAddress gaDestino = new GAddress();
		String gDestino = txtDestinoCalle.getValue() + " " + txtDestinoAltura.getValue() + ", "
				+ txtDestinoProvincia.getValue() + ", Argentina";
		gaDestino = GCoder.geocode(gDestino);
		double destinoLatitud = gaDestino.getLat();
		double destinoLongitud = gaDestino.getLng();

		// **************************************
		// ** CENTRO MAPA
		// **************************************
		Gmaps map = (Gmaps) this.getFellow(GMAP_VIAJE);
		double latCentro = (origenLatitud + destinoLatitud) / 2;
		double longCentro = (origenLongitud + destinoLongitud) / 2;

		map.setLat(latCentro);
		map.setLng(longCentro);
		map.setZoom(11);

		// **************************************
		// ** MARKER ORIGEN
		// **************************************
		// Gmarker gm = new Gmarker();
		// No usar operador !=. Podria pasar que "pepe" != "pepe"
		if (!"previewOrigen".equals(gm.getId())) {
			gm.setId("previewOrigen");
			gm.setIconImage(IMAGES__MARKER_HOUSE);
			gm.setDraggingEnabled(false);
		}
		gm.setLat(origenLatitud);
		gm.setLng(origenLongitud);
		gm.setTooltiptext("Origen: " + gOrigen);
		gm.setParent(map);

		// **************************************
		// ** MARKER DESTINO
		// **************************************
		// Gmarker gmDestino = new Gmarker();
		// No usar operador !=. Podria pasar que "pepe" != "pepe"
		if (!"previewDestino".equals(gmDestino.getId())) {
			System.out.println("entra");
			gmDestino.setId("previewDestino");
			gmDestino.setIconImage(IMAGES__MARKER_FLAG);
			gmDestino.setDraggingEnabled(false);
		}
		gmDestino.setLat(destinoLatitud);
		gmDestino.setLng(destinoLongitud);
		gmDestino.setTooltiptext("Destino: " + gDestino);
		gmDestino.setParent(map);

	}

	public void addViaje() {
		Timebox tbOrigenHora = (Timebox) this.getFellow("stOrigenHora");
		Textbox txtOrigenReferente = (Textbox) this.getFellow("stOrigenReferente");
		Textbox txtOrigenCalle = (Textbox) this.getFellow("stOrigenCalle");
		Textbox txtOrigenAltura = (Textbox) this.getFellow("stOrigenAltura");
		Textbox txtOrigenPisoDepto = (Textbox) this.getFellow("stOrigenPisoDepto");
		Textbox txtOrigenLocalidad = (Textbox) this.getFellow("stOrigenLocalidad");
		Textbox txtOrigenProvincia = (Textbox) this.getFellow("stOrigenProvincia");
		Textbox txtDestinoCalle = (Textbox) this.getFellow("stDestinoCalle");
		Textbox txtDestinoAltura = (Textbox) this.getFellow("stDestinoAltura");
		Textbox txtDestinoPisoDepto = (Textbox) this.getFellow("stDestinoPisoDepto");
		Textbox txtDestinoLocalidad = (Textbox) this.getFellow("stDestinoLocalidad");
		Textbox txtDestinoProvincia = (Textbox) this.getFellow("stDestinoProvincia");
		Textbox txtOrigenObservaciones = (Textbox) this.getFellow("stOrigenObservaciones");

		// **************************************
		// ** CREO VIAJE
		// **************************************
		Viaje v = new Viaje();
		// v.setCliente(cliente) hay que asignarle un cliente!!
		v.setHoraRequerida((tbOrigenHora.getValue()));
		v.setOrigenReferente(txtOrigenReferente.getValue());
		v.setOrigenCalle(txtOrigenCalle.getValue());
		v.setOrigenAltura(txtOrigenAltura.getValue());
		v.setOrigenPisoDepto(txtOrigenPisoDepto.getValue());
		v.setOrigenLocalidad(txtOrigenLocalidad.getValue());
		v.setOrigenProvincia(txtOrigenProvincia.getValue());
		v.setDestinoCalle(txtDestinoCalle.getValue());
		v.setDestinoAltura(txtDestinoAltura.getValue());
		v.setDestinoPisoDepto(txtDestinoPisoDepto.getValue());
		v.setDestinoLocalidad(txtDestinoLocalidad.getValue());
		v.setDestinoProvincia(txtDestinoProvincia.getValue());
		v.setOrigenObservaciones(txtOrigenObservaciones.getValue());

		// **************************************
		// ** RESUELVO COORDENADAS ORIGEN
		// **************************************
		GAddress gaOrigen = new GAddress();
		String gOrigen = txtOrigenCalle.getValue() + " " + txtOrigenAltura.getValue() + ", "
				+ txtOrigenProvincia.getValue() + ", Argentina";
		gaOrigen = GCoder.geocode(gOrigen);
		v.setOrigenLatitud(gaOrigen.getLat());
		v.setOrigenLongitud(gaOrigen.getLng());

		// **************************************
		// ** RESUELVO COORDENADAS DESTINO
		// **************************************
		GAddress gaDestino = new GAddress();
		String gDestino = txtDestinoCalle.getValue() + " " + txtDestinoAltura.getValue() + ", "
				+ txtDestinoProvincia.getValue() + ", Argentina";
		gaDestino = GCoder.geocode(gDestino);
		v.setDestinoLatitud(gaDestino.getLat());
		v.setDestinoLongitud(gaDestino.getLng());

		// **************************************
		// ** GUARDAMOS EL VIAJE EN LA CENTRAL
		// **************************************
		CentralTaxis.getInstance().addViaje(v);
		System.out.println("id de viaje: " + v.getIdViaje());
	}

	public Collection<Cliente> getListaClientes() {
		SortedSet<Cliente> lista = new TreeSet<Cliente>(CentralTaxis.getInstance().getClientes());
		return lista;
	}

}
