package ar.edu.utn.frba.proyecto.citysoft.controller.abmCliente;

import org.zkoss.zul.Window;
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class VentanaAbmCliente extends Window {

	public void altaCliente() {
		Intbox intLegajo = (Intbox) this.getFellow("intLegajo");
		Textbox txtNombres = (Textbox) this.getFellow("txtNombres");
		Textbox txtApellido = (Textbox) this.getFellow("txtApellido");
		// Combobox txtTipoDoc = (Combobox) this.getFellow("cmbTipoDoc");
		Intbox intNroDoc = (Intbox) this.getFellow("intNroDoc");
		Textbox txtDireccion = (Textbox) this.getFellow("txtDireccion");
		Textbox txtLocalidad = (Textbox) this.getFellow("txtLocalidad");
		Intbox intTelefono = (Intbox) this.getFellow("intTelefono");

		// **************************************
		// ** Creo el cliente!!!
		// **************************************

		Cliente c = new Cliente();
		c.setIdCliente(intLegajo.getValue());
		c.setApellido(txtApellido.getValue());
		c.setNombre(txtNombres.getValue());
		c.setDni(intNroDoc.getValue());
		c.setDireccion(txtDireccion.getValue());
		c.setLocalidad(txtLocalidad.getValue());
		c.setTelefono(String.valueOf(intTelefono.getValue()));

		// **************************************
		// ** Guardo el cliente en la central!!!!
		// **************************************

		CentralTaxis.getInstance().addCliente(c);
	}

}
