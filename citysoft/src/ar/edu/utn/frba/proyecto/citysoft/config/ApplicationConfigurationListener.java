package ar.edu.utn.frba.proyecto.citysoft.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.ReceptorNmea;

public class ApplicationConfigurationListener implements ServletContextListener {

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

		// Por úlitmo, inicializamos el actualizador de posicionamiento
		new Thread(new ReceptorNmea()).start();
	}

}
