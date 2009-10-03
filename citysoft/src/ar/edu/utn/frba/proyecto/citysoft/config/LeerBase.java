package ar.edu.utn.frba.proyecto.citysoft.config;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Taxi;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class LeerBase {

	// ***************************************
	// ** Constructor(s)
	// ***************************************

	private LeerBase() {
	}

	private static final LeerBase thiz = new LeerBase();

	public static LeerBase getInstance() {
		return thiz;
	}

	// ***************************************
	// ** Execution
	// ***************************************

	public void mostrarObjetos() {
		ObjectContainer db = ContextoAplicacion.getInstance().obtenerDb();
		ObjectSet<Taxi> query = db.query(Taxi.class);
		while (query.hasNext()) {
			System.out.println(query.next());
		}
	}

	// **************************************
	// ** MAIN - Para crear la DB
	// **************************************

	public static void main(String[] args) {
		ContextoAplicacion.getInstance().abrirDb();
		// CentralTaxis.getInstance().initialize();
		LeerBase.getInstance().mostrarObjetos();
		ContextoAplicacion.getInstance().cerrarDd();
	}

}
