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
	private int idViaje;

	// **************************************
	// ** Constructor
	// **************************************

	public static GeneradorDeIds getInstance() {
		ObjectSet<GeneradorDeIds> set = getDb().query(GeneradorDeIds.class);
		if (set.hasNext()) {
			return set.next();
		} else {
			GeneradorDeIds generadorDeIds = new GeneradorDeIds();
			getDb().store(generadorDeIds);
			return generadorDeIds;
		}
	}

	private GeneradorDeIds() {
		this.idCliente = 1;
		this.idConductor = 1;
		this.idViaje = 1;
		getDb().store(this);
	}

	// **************************************
	// ** Accessors
	// **************************************

	public int getProximoIdViaje() {
		int i = this.idViaje++;
		getDb().store(this);
		return i;
	}

	public int getProximoIdCliente() {
		int i = this.idCliente++;
		getDb().store(this);
		return i;
	}

	public int getProximoIdConductor() {
		int idConductor2 = this.idConductor++;
		getDb().store(this);
		return idConductor2;
	}

	// **************************************
	// ** Helpers
	// **************************************

	private static ObjectContainer getDb() {
		return ContextoAplicacion.getInstance().obtenerDb();
	}

}
