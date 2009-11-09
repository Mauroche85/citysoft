package ar.edu.utn.frba.proyecto.citysoft.config;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeClientes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeConductoresVehiculosYViajes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.LoteDeVehiculosDesactivados;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaClientes;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaVehiculoReal;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaVehiculosSimulados;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega.LoteDeEntregaViajeReal;

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
		this.lotes.add(new LoteDeEntregaViajeReal());
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
		Central.getInstance().terminate();
	}

	public void borrarYPoblarBase() {
		ContextoAplicacion.getInstance().borrarDb();
		Central.getInstance().initialize();
		AmbienteDeDesarrollo.getInstance().cargar();
	}

}
