package ar.edu.utn.frba.proyecto.citysoft.controller.abmCliente;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class VentanaAbmCliente extends Window {

	private static final long serialVersionUID = 8473212146219211650L;

	// **************************************
	// ** ELEMENTOS
	// **************************************

	private Textbox elemTelefono() {
		return (Textbox) this.getFellow("txtTelefono");
	}

	private Textbox elemLocalidad() {
		return (Textbox) this.getFellow("txtLocalidad");
	}

	private Textbox elemDireccion() {
		return (Textbox) this.getFellow("txtDireccion");
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

	private Intbox elemLegajo() {
		return (Intbox) this.getFellow("intLegajo");
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

	public void abrirDetalle(int idCliente) {
		// Primero deshabilitamos los campos
		elemLegajo().setReadonly(true);
		elemNombres().setReadonly(true);
		elemApellido().setReadonly(true);
		elemNroDocumento().setReadonly(true);
		elemDireccion().setReadonly(true);
		elemLocalidad().setReadonly(true);
		elemTelefono().setReadonly(true);
		// Luego, traemos los valores
		traerDetalleCliente(idCliente);
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
		elemLegajo().setReadonly(true);
		elemNombres().setReadonly(false);
		elemApellido().setReadonly(false);
		elemNroDocumento().setReadonly(false);
		elemDireccion().setReadonly(false);
		elemLocalidad().setReadonly(false);
		elemTelefono().setReadonly(false);
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

	public void abrirModificacion(int idCliente) {
		traerDetalleCliente(idCliente);
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

	public void altaCliente() {
		// Creo el cliente!!!
		Cliente c = new Cliente();
		c.setIdCliente(elemLegajo().getValue());
		c.setApellido(elemApellido().getValue());
		c.setNombre(elemNombres().getValue());
		// c.setTipoDocumento(elemTipoDocumento().getValue());
		c.setDni(elemNroDocumento().getValue());
		c.setDireccion(elemDireccion().getValue());
		c.setLocalidad(elemLocalidad().getValue());
		c.setTelefono(elemTelefono().getValue());

		// Guardo el cliente en la central!!!!
		CentralTaxis.getInstance().addCliente(c);

		// Cierro la ventana
		this.cerrar();
	}

	public void modifCliente() {
		// Creo el cliente!!!
		Cliente c = CentralTaxis.getInstance().getClientePorId(elemLegajo().getValue());

		c.setApellido(elemApellido().getValue());
		c.setNombre(elemNombres().getValue());
		// c.setTipoDocumento(elemTipoDocumento().getValue());
		c.setDni(elemNroDocumento().getValue());
		c.setDireccion(elemDireccion().getValue());
		c.setLocalidad(elemLocalidad().getValue());
		c.setTelefono(elemTelefono().getValue());

		// Actualizo el cliente en la central!!!!
		CentralTaxis.getInstance().addCliente(c);

		// Cierro la ventana
		this.cerrar();
	}

	public void eliminarCliente() {
		int idCliente = elemLegajo().intValue();
		Cliente c = CentralTaxis.getInstance().getClientePorId(idCliente);
		CentralTaxis.getInstance().getClientes().remove(c);
		this.cerrar();
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void traerDetalleCliente(int idCliente) {
		Cliente c = CentralTaxis.getInstance().getClientePorId(idCliente);
		elemLegajo().setValue(idCliente);
		elemNombres().setValue(c.getNombre());
		elemApellido().setValue(c.getApellido());
		elemNroDocumento().setValue(c.getDni());
		elemDireccion().setValue(c.getDireccion());
		elemLocalidad().setValue(c.getLocalidad());
		elemTelefono().setValue(c.getTelefono());
	}

	private void limpiarCampos() {
		elemLegajo().setValue(null);
		elemNombres().setValue(null);
		elemApellido().setValue(null);
		elemNroDocumento().setValue(null);
		elemDireccion().setValue(null);
		elemLocalidad().setValue(null);
		elemTelefono().setValue(null);
	}

}
