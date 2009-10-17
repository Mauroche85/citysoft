package ar.edu.utn.frba.proyecto.citysoft.controller.bienvenidaCliente;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;
import org.zkoss.zul.api.Timebox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

import com.blogspot.unserializableone.GAddress;
import com.blogspot.unserializableone.GCoder;

public class pedidosCliente extends Window {
	Gmarker gm = new Gmarker();
	Gmarker gmDestino = new Gmarker();

	public void previewViaje() {
		Textbox txtOrigenCalle = (Textbox) this.getFellow("stOrigenCalle");
		Textbox txtOrigenAltura = (Textbox) this.getFellow("stOrigenAltura");
		Textbox txtOrigenProvincia = (Textbox) this.getFellow("stOrigenProvincia");
		Textbox txtDestinoCalle = (Textbox) this.getFellow("stDestinoCalle");
		Textbox txtDestinoAltura = (Textbox) this.getFellow("stDestinoAltura");
		Textbox txtDestinoProvincia = (Textbox) this.getFellow("stDestinoProvincia");
		Textbox txtOrigenPisoDepto = (Textbox) this.getFellow("stOrigenPisoDepto");
		String gOrigen = new String();

		GAddress gaOrigen = new GAddress();
		gOrigen = txtOrigenCalle.getValue() + " " + txtOrigenAltura.getValue() + ", "
				+ txtOrigenProvincia.getValue() + ", Argentina";
		try {
			gaOrigen = GCoder.geocode(gOrigen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double origenLatitud = gaOrigen.getLat();
		double origenLongitud = gaOrigen.getLng();

		String gDestino = new String();

		GAddress gaDestino = new GAddress();
		gDestino = txtDestinoCalle.getValue() + " " + txtDestinoAltura.getValue() + ", "
				+ txtDestinoProvincia.getValue() + ", Argentina";
		try {
			gaDestino = GCoder.geocode(gDestino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double destinoLatitud = gaDestino.getLat();
		double destinoLongitud = gaDestino.getLng();

		System.out.println("Coord origen: " + origenLatitud + " " + origenLongitud);

		
		
		Gmaps map = (Gmaps) this.getFellow("gmapPedido");
		double latCentro = (origenLatitud+destinoLatitud)/2; 
		double longCentro = (origenLongitud+destinoLongitud)/2;
		
		
		map.setLat(latCentro);
		map.setLng(longCentro);
		
		map.setZoom(11);

//		Gmarker gm = new Gmarker();
		if (gm.getId()!="previewOrigen"){
			gm.setId("previewOrigen");
			 gm.setIconImage("images/markerHouse.png");
			 gm.setDraggable(null);

			}
		gm.setLat(origenLatitud);
		gm.setLng(origenLongitud);
		gm.setTooltiptext("Origen: " + gOrigen);
		gm.setParent(map);	
	//gd
//		Gmarker gmDestino = new Gmarker();
		if (gmDestino.getId()!="previewDestino")
			{
			System.out.println("entra");
			gmDestino.setId("previewDestino");
			gmDestino.setIconImage("images/markerHouse.png");
			gmDestino.setDraggable(null);

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

		/*
		 * crear pedido
		 */
		Viaje v = new Viaje();
		// v.setCliente(cliente) hay que asignarle un cliente!!
		// v.setIdViaje(idViaje) hay que obtener el id de viaje!!!
		int idViaje = CentralTaxis.getInstance().getGeneradorDeIds().getProximoIdViaje();
		v.setIdViaje(idViaje);
		v.setHoraPedido((tbOrigenHora.getValue()));
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

		String gOrigen = new String();

		GAddress gaOrigen = new GAddress();
		gOrigen = txtOrigenCalle.getValue() + " " + txtOrigenAltura.getValue() + ", "
				+ txtOrigenProvincia.getValue() + ", Argentina";
		try {
			gaOrigen = GCoder.geocode(gOrigen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		v.setOrigenLatitud(gaOrigen.getLat());
		v.setOrigenLongitud(gaOrigen.getLng());

		String gDestino = new String();

		GAddress gaDestino = new GAddress();
		gDestino = txtDestinoCalle.getValue() + " " + txtDestinoAltura.getValue() + ", "
				+ txtDestinoProvincia.getValue() + ", Argentina";
		try {
			gaDestino = GCoder.geocode(gDestino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		v.setDestinoLatitud(gaDestino.getLat());
		v.setDestinoLongitud(gaDestino.getLng());

		CentralTaxis.getInstance().addViaje(v);

		/*		

	*/

		System.out.println("id de viaje: " + idViaje);
		// System.out.println("dire origen: " + gaResultado);

	}

}
