package ar.edu.utn.frba.proyecto.citysoft.config;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeClientes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeConductoresTaxisYViajes;

/**
 * Ver el javadoc de {@link Lote}.
 * 
 * Carga todos los objetos del modelo de la aplicaci�n. Dicho de otra manera,
 * esto es un cat�logo de lotes. Sirve para que la aplicaci�n, al levantarse, le
 * diga a este cat�logo que se cargue y as� levante todos los objetos y sus
 * relaciones a memoria.
 * 
 * @author Alejandro
 */
public class AmbienteDeDesarrollo {

	private List<Lote> lotes = new ArrayList<Lote>();

	// ***************************************
	// ** Constructor(s)
	// ***************************************

	private AmbienteDeDesarrollo() {
		this.configure();
	}

	private static final AmbienteDeDesarrollo thiz = new AmbienteDeDesarrollo();

	public static AmbienteDeDesarrollo getInstance() {
		return thiz;
	}

	// ***************************************
	// ** Configuration
	// ***************************************

	private void configure() {
		// TODO mantener la configuraci�n actualizada. Ir completando con los
		// sucesivos lotes
		this.lotes.add(new LoteDeClientes());
		this.lotes.add(new LoteDeConductoresTaxisYViajes());
	}

	// ***************************************
	// ** Execution
	// ***************************************

	public void cargar() {
		for (Lote lote : this.lotes) {
			lote.cargar();
		}
	}

	public void crearAmbiente() {
		ContextoAplicacion.getInstance().abrirDb();
		borrarYPoblarBase();
		ContextoAplicacion.getInstance().cerrarDd();
		CentralTaxis.getInstance().terminate();
	}

	public void borrarYPoblarBase() {
		ContextoAplicacion.getInstance().borrarDb();
		CentralTaxis.getInstance().initialize();
		AmbienteDeDesarrollo.getInstance().cargar();
	}

}
