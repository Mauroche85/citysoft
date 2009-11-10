package ar.edu.utn.frba.proyecto.citysoft.config;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

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
		ObjectSet<Vehiculo> query = db.query(Vehiculo.class);
		while (query.hasNext()) {
			System.out.println(query.next());
		}
	}

	// **************************************
	// ** MAIN - Para crear la DB
	// **************************************

	public static void main(String[] args) {
		AmbienteDeDesarrollo.getInstance().crearAmbiente();
		// Empezamos de 0
		ContextoAplicacion.getInstance().abrirDb();
		// Central.getInstance().initialize();
		LeerBase.getInstance().mostrarObjetos();
		ContextoAplicacion.getInstance().cerrarDd();
	}

}
