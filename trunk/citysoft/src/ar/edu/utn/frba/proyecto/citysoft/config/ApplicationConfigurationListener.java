package ar.edu.utn.frba.proyecto.citysoft.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.ReceptorNmea;

public class ApplicationConfigurationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO mantener la configuración actualizada

		// Levantamos el lote de objetos inicial
		AmbienteDeDesarrollo.getInstance().cargar();
		// Por úlitmo, inicializamos el actualizador de posicionamiento
		new Thread(new ReceptorNmea()).start();
	}

}
