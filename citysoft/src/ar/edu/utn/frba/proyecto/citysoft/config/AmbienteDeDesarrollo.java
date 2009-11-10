package ar.edu.utn.frba.proyecto.citysoft.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeClientes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeConductoresVehiculosYViajes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeVehiculosDesactivados;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaClientes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaVehiculoReal;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaVehiculosSimulados;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaViajes;

/**
 * Ver el javadoc de {@link Lote}.
 * 
 * Carga todos los objetos del modelo de la aplicación. Dicho de otra manera,
 * esto es un catálogo de lotes. Sirve para que la aplicación, al levantarse, le
 * diga a este catálogo que se cargue y así levante todos los objetos y sus
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
		// TODO mantener la configuración actualizada. Ir completando con los
		// sucesivos lotes
		//
		// configureLoteDeDesarrollo();
		configureLoteDeEntrega();
	}

	private void configureLoteDeDesarrollo() {
		this.lotes.add(new LoteDeClientes());
		this.lotes.add(new LoteDeConductoresVehiculosYViajes());
		this.lotes.add(new LoteDeVehiculosDesactivados());
	}

	private void configureLoteDeEntrega() {
		this.lotes.add(new LoteDeEntregaClientes());
		this.lotes.add(new LoteDeEntregaVehiculosSimulados());
		this.lotes.add(new LoteDeEntregaVehiculoReal());
		this.lotes.add(new LoteDeEntregaViajes());
	}

	// ***************************************
	// ** Execution
	// ***************************************

	public void cargar() {
		for (Lote lote : this.lotes) {
			getLog().info("Cargando lote: " + lote);
			lote.cargar();
			getLog().info("Lote cargado exitosamente");
		}
		// Re-inicializamos la central
		Central.reset();
	}

	public void crearAmbiente() {
		ContextoAplicacion.getInstance().abrirDb();
		borrarYPoblarBase();
		ContextoAplicacion.getInstance().cerrarDd();
	}

	public void borrarYPoblarBase() {
		ContextoAplicacion.getInstance().borrarDb();
		AmbienteDeDesarrollo.getInstance().cargar();
	}

	// **************************************
	// ** Helpers
	// **************************************

	private Log getLog() {
		return LogFactory.getLog(this.getClass());
	}

}
