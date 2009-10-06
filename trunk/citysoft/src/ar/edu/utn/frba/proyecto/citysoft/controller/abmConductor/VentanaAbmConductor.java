package ar.edu.utn.frba.proyecto.citysoft.controller.abmConductor;

import org.zkoss.zul.Window;
import org.zkoss.zul.api.Combobox;
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Conductor;

public class VentanaAbmConductor extends Window {

	private static int i = 900;

	public void altaConductor() {
		Textbox txtNombres = (Textbox) this.getFellow("txtNombres");
		Textbox txtApellido = (Textbox) this.getFellow("txtApellido");

		Combobox txtTipoDoc = (Combobox) this.getFellow("cmbTipoDoc");
		Intbox intNroDoc = (Intbox) this.getFellow("intNroDoc");

		Textbox txtDireccion = (Textbox) this.getFellow("txtDireccion");
		Textbox txtLocalidad = (Textbox) this.getFellow("txtLocalidad");
		Intbox intTelefono = (Intbox) this.getFellow("intTelefono");

		// **************************************
		// ** Creo el conductor!!!
		// **************************************

		Conductor c = new Conductor();
		c.setIdConductor(999);
		c.setApellido(txtApellido.getValue());
		c.setNombre(txtNombres.getValue());
		// TODO Agregar el tipo del documento al conductor
		c.setDni(intNroDoc.getValue());
		c.setDireccion(txtDireccion.getValue());
		// TODO agregar localidad al conductor
		c.setTelefono(String.valueOf(intTelefono.getValue()));

		// **************************************
		// ** Guardo el conductor en la central!!!!
		// **************************************
		CentralTaxis.getInstance().addConductor(c);
	}

}
