package ar.edu.utn.frba.proyecto.citysoft.controller.bienvenidaCliente;

import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;
import org.zkoss.zul.api.Timebox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class pedidosCliente extends Window {

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
//		v.setCliente(cliente) hay que asignarle un cliente!!
//		v.setIdViaje(idViaje) hay que obtener el id de viaje!!!
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
		 
		CentralTaxis.getInstance().addViaje(v);
		
		
		/*		

	*/
		
		
		
		System.out.println("la hora es: " + tbOrigenHora.getValue());
		System.out.println("la hora es: " + txtOrigenReferente.getValue());		
	}

}
