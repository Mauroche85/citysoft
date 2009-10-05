package ar.edu.utn.frba.proyecto.citysoft.controller.abmVehiculo;

import org.zkoss.zul.Window;
import org.zkoss.zul.api.Combobox;
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

public class VentanaAbmVehiculo extends Window {

	public void altaVehiculo() {
		Textbox txtNombres = (Textbox) this.getFellow("txtNombres");
		Textbox txtApellido = (Textbox) this.getFellow("txtApellido");

		Combobox txtTipoDoc = (Combobox) this.getFellow("cmbTipoDoc");
		Intbox intNroDoc = (Intbox) this.getFellow("intNroDoc");

		Textbox txtPatente = (Textbox) this.getFellow("txtPatente");
		Textbox txtMarca = (Textbox) this.getFellow("txtMarca");
		Textbox txtModelo = (Textbox) this.getFellow("txtModelo");
		Textbox txtPoliza = (Textbox) this.getFellow("txtPoliza");
		
		Combobox txtTipoVeh = (Combobox) this.getFellow("cmbTipoVeh");

		// **************************************
		// ** Creo el vehiculo!!!
		// **************************************

		Taxi t = new Taxi();
		t.setIdTaxi(888);
		t.setMarca(txtMarca.getValue());
		t.setModelo(txtModelo.getValue());
		
		// **************************************
		// ** Guardo el taxi en la central!!!!
		// **************************************

		CentralTaxis.getInstance().addTaxi(t);
	}

}
