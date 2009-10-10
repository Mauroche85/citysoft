package ar.edu.utn.frba.proyecto.citysoft.modelo;

import ar.edu.utn.frba.proyecto.citysoft.config.ContextoAplicacion;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Esta clase solo debería ser instanciada por la central de taxis!!!
 * 
 * @author Alejandro
 */
public class GeneradorDeIds {

	private int idCliente;
	private int idConductor;

	// **************************************
	// ** Constructor
	// **************************************

	public static GeneradorDeIds getInstance() {
		ObjectSet<GeneradorDeIds> set = getDb().query(GeneradorDeIds.class);
		if (set.hasNext()) {
			return set.next();
		} else
			return new GeneradorDeIds();
	}

	private GeneradorDeIds() {
		this.idCliente = 1;
		this.idConductor = 1;
		getDb().store(this);
	}

	// **************************************
	// ** Accessors
	// **************************************

	public int getProximoIdCliente() {
		return this.idCliente++;
	}

	public int getProximoIdConductor() {
		return this.idConductor;
	}

	// **************************************
	// ** Helpers
	// **************************************

	private static ObjectContainer getDb() {
		return ContextoAplicacion.getInstance().obtenerDb();
	}

}
