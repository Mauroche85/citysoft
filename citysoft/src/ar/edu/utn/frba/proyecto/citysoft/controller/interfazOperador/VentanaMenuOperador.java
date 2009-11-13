package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import java.io.IOException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.config.AmbienteDeDesarrollo;
import ar.edu.utn.frba.proyecto.citysoft.config.ArchivoDeConfiguracion;
import ar.edu.utn.frba.proyecto.citysoft.controller.abmCliente.VentanaAbmCliente;
import ar.edu.utn.frba.proyecto.citysoft.controller.abmConductor.VentanaAbmConductor;
import ar.edu.utn.frba.proyecto.citysoft.controller.abmVehiculo.VentanaAbmVehiculo;

public class VentanaMenuOperador extends Window implements ConstantesInterfazOperador {

	private static final long serialVersionUID = -367746877845172163L;

	// **************************************
	// ** ELEMENTOS
	// **************************************

	public MapaOperador elemMapa() {
		return (MapaOperador) this.getFellow(COMP__GMAP_OPERADOR);
	}

	// **************************************
	// ** ACCIONES
	// **************************************

	public void abrirInterfazOperador() throws IOException {
		if (ArchivoDeConfiguracion.getInstance().getInterfazOperadorNueva())
			Executions.createComponents(ZUL__CONTROL_OPERADOR, null, null);
		else
			Executions.sendRedirect(ZUL__INTERFAZ_OPERADOR);
	}

	public void resetearBase() {
		AmbienteDeDesarrollo.getInstance().borrarYPoblarBase();
	}

	// **************************************
	// ** ACCIONES - Abrir listas
	// **************************************

	public void abrirListaClientes() {
		Executions.createComponents(ZUL__LISTA_CLIENTES, null, null);
	}

	public void abrirListaConductores() {
		Executions.createComponents(ZUL__LISTA_CONDUCTORES, null, null);
	}

	public void abrirListaVehiculos() {
		Executions.createComponents(ZUL__LISTA_VEHICULOS, null, null);
	}

	// **************************************
	// ** ACCIONES - Abrir altas
	// **************************************

	public void abrirAltaCliente() {
		VentanaAbmCliente abm;
		abm = (VentanaAbmCliente) Executions.createComponents(ZUL__ALTA_CLIENTE, null, null);
		abm.abrirAlta();
	}

	public void abrirAltaConductor() {
		VentanaAbmConductor abm;
		abm = (VentanaAbmConductor) Executions.createComponents(ZUL__ALTA_CONDUCTOR, null, null);
		abm.abrirAlta();
	}

	public void abrirAltaVehiculo() {
		VentanaAbmVehiculo abm;
		abm = (VentanaAbmVehiculo) Executions.createComponents(ZUL__ALTA_VEHICULO, null, null);
		abm.abrirAlta();
	}

}
