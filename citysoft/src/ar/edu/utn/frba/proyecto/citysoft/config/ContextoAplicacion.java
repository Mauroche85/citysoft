package ar.edu.utn.frba.proyecto.citysoft.config;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Aca estan todas las cosas que son comunes a todos y a la webapp (aplicacion
 * web) entera.
 * 
 * @author Alejandro
 */
public class ContextoAplicacion {

	// **************************************
	// ** Constructor
	// **************************************

	private static final String DEVELOPMENT_DB_FILE = "development.db4o";

	private ContextoAplicacion() {
	}

	private static ContextoAplicacion thiz = new ContextoAplicacion();

	public static ContextoAplicacion getInstance() {
		return thiz;
	}

	// **************************************
	// ** Atributos
	// **************************************

	private ObjectContainer db;

	// **************************************
	// ** Ejecucion
	// **************************************

	public void abrirDb() {
		// Db4o.newConfiguration().objectClass(Vehiculo.class).cascadeOnUpdate(true);
		Db4o.configure().objectClass(Vehiculo.class).cascadeOnUpdate(true);
		this.db = Db4o.openFile(DEVELOPMENT_DB_FILE);
	}

	public void borrarDb() {
		ObjectSet<?> result = db.get(new Object());
		while (result.hasNext()) {
			db.delete(result.next());
		}
	}

	public ObjectContainer obtenerDb() {
		return this.db;
	}

	public void commit() {
		this.db.commit();
	}

	public void cerrarDd() {
		this.commit();
		this.db.close();
		this.db = null;
	}

}
