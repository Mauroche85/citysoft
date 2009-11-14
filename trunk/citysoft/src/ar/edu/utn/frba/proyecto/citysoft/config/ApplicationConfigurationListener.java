package ar.edu.utn.frba.proyecto.citysoft.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.ReceptorAgwClient;
import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.Simulaciones;
import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.SimuladorNmeaGttr;

public class ApplicationConfigurationListener implements ServletContextListener, Simulaciones {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ContextoAplicacion.getInstance().cerrarDd();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ArchivoDeConfiguracion config = ArchivoDeConfiguracion.getInstance();

		// Para poblar la base de datos, la primera vez que se ejecuta!
		if (config.getPoblarBase())
			AmbienteDeDesarrollo.getInstance().crearAmbiente();

		ContextoAplicacion.getInstance().abrirDb();
		// inicializo la central
		Central.getInstance().initialize();

		// Inicializamos el simulador interno de posicionamiento
		new Thread(new SimuladorNmeaGttr(SIM__LINDA, SIM__LINDA_FILE)).start();
		// Inicializamos el simulador externo de posicionamiento
		// new Thread(new ReceptorNmea()).start();
		// Por úlitmo, inicializamos el actualizador de posicionamiento
		if (ArchivoDeConfiguracion.getInstance().getReceptorHabilitado()) {
			new Thread(new ReceptorAgwClient()).start();
		}
	}

}
