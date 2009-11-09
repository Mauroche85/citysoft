package ar.edu.utn.frba.proyecto.citysoft.modelo;

import ar.edu.utn.frba.proyecto.citysoft.config.ContextoAplicacion;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Esta clase solo debería ser instanciada por la central!!!
 * 
 * Los IDs generados son desde 1000 hacia arriba ya que dejamos una franja
 * (bastante grande...) para que los lotes puedan generar a gusto sin conflictos
 * de IDs.
 * 
 * @author Alejandro
 */
public class GeneradorDeIds {

	private int idCliente;
	private int idConductor;
	private int idVehiculo;
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
		this.idCliente = 1000;
		this.idConductor = 1000;
		this.idViaje = 1000;
		this.idVehiculo = 1000;
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
		int i = this.idConductor++;
		getDb().store(this);
		return i;
	}

	public int getProximoIdVehiculo() {
		int i = this.idVehiculo++;
		getDb().store(this);
		return i;
	}

	// **************************************
	// ** Helpers
	// **************************************

	private static ObjectContainer getDb() {
		return ContextoAplicacion.getInstance().obtenerDb();
	}

}
