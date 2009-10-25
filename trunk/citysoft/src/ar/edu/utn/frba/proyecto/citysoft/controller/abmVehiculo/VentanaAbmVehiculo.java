package ar.edu.utn.frba.proyecto.citysoft.controller.abmVehiculo;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class VentanaAbmVehiculo extends Window {

	private static final long serialVersionUID = 8473212146219211650L;

	// **************************************
	// ** ELEMENTOS
	// **************************************

	private Intbox elemIdVehiculo() {
		return (Intbox) this.getFellow("intIdVehiculo");
	}

	private Textbox elemPoliza() {
		return (Textbox) this.getFellow("txtPoliza");
	}

	private Textbox elemMarca() {
		return (Textbox) this.getFellow("txtMarca");
	}

	private Textbox elemModelo() {
		return (Textbox) this.getFellow("txtModelo");
	}

	private Intbox elemNroDocumento() {
		return (Intbox) this.getFellow("intNroDoc");
	}

	private Textbox elemApellido() {
		return (Textbox) this.getFellow("txtApellido");
	}

	private Textbox elemNombres() {
		return (Textbox) this.getFellow("txtNombres");
	}

	private Textbox elemPatente() {
		return (Textbox) this.getFellow("txtPatente");
	}

	// **************************************
	// ** ELEMENTOS - BOTONES
	// **************************************

	private Button elemModificar() {
		return (Button) this.getFellow("btnModificar");
	}

	private Button elemEliminar() {
		return (Button) this.getFellow("btnEliminar");
	}

	private Button elemCerrar() {
		return (Button) this.getFellow("btnCerrar");
	}

	private Button elemGuardarAlta() {
		return (Button) this.getFellow("btnGuardarAlta");
	}

	private Button elemGuardarModif() {
		return (Button) this.getFellow("btnGuardarModif");
	}

	private Button elemCancelar() {
		return (Button) this.getFellow("btnCancelar");
	}

	// **************************************
	// ** Estados de la ventana
	// **************************************

	public void abrirAlta() {
		limpiarCampos();
		elemModificar().setVisible(false);
		elemEliminar().setVisible(false);
		elemCerrar().setVisible(false);
		elemGuardarAlta().setVisible(true);
		elemGuardarModif().setVisible(false);
		elemCancelar().setVisible(true);
	}

	public void abrirDetalle(int idVehiculo) {
		// Primero deshabilitamos los campos
		elemIdVehiculo().setReadonly(true);
		elemPatente().setReadonly(true);
		elemNombres().setReadonly(true);
		elemApellido().setReadonly(true);
		elemNroDocumento().setReadonly(true);
		elemPoliza().setReadonly(true);
		elemMarca().setReadonly(true);
		elemModelo().setReadonly(true);
		// Luego, traemos los valores
		traerDetalleVehiculo(idVehiculo);
		// Despues, habilitamos y deshabilitamos los botones
		elemModificar().setVisible(true);
		elemEliminar().setVisible(true);
		elemCerrar().setVisible(true);
		elemGuardarAlta().setVisible(false);
		elemGuardarModif().setVisible(false);
		elemCancelar().setVisible(false);
		// Finalmente, abrimos la ventana
		this.setVisible(true);
	}

	public void abrirModificacion() {
		// Habilitamos los campos pertientes
		elemIdVehiculo().setReadonly(true);
		elemPatente().setReadonly(true);
		elemNombres().setReadonly(false);
		elemApellido().setReadonly(false);
		elemNroDocumento().setReadonly(false);
		elemPoliza().setReadonly(false);
		elemMarca().setReadonly(false);
		elemModelo().setReadonly(false);
		// Despues, habilitamos y deshabilitamos los botones
		elemModificar().setVisible(false);
		elemEliminar().setVisible(false);
		elemCerrar().setVisible(false);
		elemGuardarAlta().setVisible(false);
		elemGuardarModif().setVisible(true);
		elemCancelar().setVisible(true);
		// Finalmente, abrimos la ventana
		this.setVisible(true);
	}

	public void abrirModificacion(int idVehiculo) {
		traerDetalleVehiculo(idVehiculo);
		this.abrirModificacion();
	}

	/**
	 * Si nos abren desde otra ventana, la otra ventana se puede colgar del
	 * evento ON_CLOSE de esta ventana para hacer cosas porque para cerrar
	 * posteamos un ON_CLOSE. Si fuesemos a cerrar la ventana llamando a
	 * detach(), el evento ON_CLOSE no se postea y la ventana padre no tiene
	 * forma de enterarse si esta se cierra o no
	 */
	public void cerrar() {
		Events.postEvent(new Event(Events.ON_CLOSE, this));
	}

	// **************************************
	// ** Ejecucion
	// **************************************

	public void altaVehiculo() {
		// Creo el vehiculo!!!
		Vehiculo c = new Vehiculo();
		c.setIdVehiculo(elemIdVehiculo().getValue());
		c.setPatente(elemPatente().getValue());
		c.setApellido(elemApellido().getValue());
		c.setNombre(elemNombres().getValue());
		// c.setTipoDocumento(elemTipoDocumento().getValue());
		c.setDni(elemNroDocumento().getValue());
		c.setPoliza(elemPoliza().getValue());
		c.setMarca(elemMarca().getValue());
		c.setModelo(elemModelo().getValue());

		// Guardo el cliente en la central!!!!
		Central.getInstance().addVehiculo(c);

		// Cierro la ventana
		this.cerrar();
	}

	public void modifVehiculo() {
		// Creo el vehiculo!!!
		Vehiculo c = Central.getInstance().getVehiculoPorId(elemIdVehiculo().getValue());
		c.setPatente(elemPatente().getValue());
		c.setApellido(elemApellido().getValue());
		c.setNombre(elemNombres().getValue());
		// c.setTipoDocumento(elemTipoDocumento().getValue());
		c.setDni(elemNroDocumento().getValue());
		c.setPoliza(elemPoliza().getValue());
		c.setMarca(elemMarca().getValue());
		c.setModelo(elemModelo().getValue());

		// Actualizo el cliente en la central!!!!
		Central.getInstance().addVehiculo(c);

		// Cierro la ventana
		this.cerrar();
	}

	public void eliminarVehiculo() {
		int idVehiculo = elemIdVehiculo().getValue();
		Vehiculo c = Central.getInstance().getVehiculoPorId(idVehiculo);
		Central.getInstance().getVehiculos().remove(c);
		this.cerrar();
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void traerDetalleVehiculo(int idVehiculo) {
		Vehiculo c = Central.getInstance().getVehiculoPorId(idVehiculo);
		elemIdVehiculo().setValue(idVehiculo);
		elemPatente().setValue(c.getPatente());
		elemNombres().setValue(c.getNombre());
		elemApellido().setValue(c.getApellido());
		elemNroDocumento().setValue(c.getDni());
		elemPoliza().setValue(c.getPoliza());
		elemMarca().setValue(c.getMarca());
		elemModelo().setValue(c.getModelo());
	}

	private void limpiarCampos() {
		elemPatente().setValue(null);
		elemNombres().setValue(null);
		elemApellido().setValue(null);
		elemNroDocumento().setValue(null);
		elemPoliza().setValue(null);
		elemMarca().setValue(null);
		elemModelo().setValue(null);
	}

}
