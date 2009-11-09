package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ar.edu.utn.frba.proyecto.citysoft.config.ArchivoDeConfiguracion;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

/**
 * El simulador NMEA GTTR es nuestro simulador. Sirve para generar tramas en
 * base a un recorrido particular, que serán inyectadas a diferentes vehiculos.
 * 
 * La misma simulación puede ser usada para diferentes vehiculos. La unica
 * diferencia es que algunos comienzan a simular unos segundos mas tarde.
 */
public class SimuladorNmeaGttr implements Runnable {

	// **************************************
	// ** Atributos
	// **************************************

	private String nombreSimulacion;
	private String archivoSimulacion;
	private List<Coordenadas> listaCoordenadas = new ArrayList<Coordenadas>();

	// **************************************
	// ** Constructor
	// **************************************

	public SimuladorNmeaGttr(String nombreSimulacion, String archivoSimulacion) {
		this.nombreSimulacion = nombreSimulacion;
		this.archivoSimulacion = archivoSimulacion;
	}

	// **************************************
	// ** Execution
	// **************************************

	/**
	 * Empieza a leer actualizaciones de la información de localización
	 */
	@Override
	public void run() {
		List<Integer> idsVehiculosSimulados = ArchivoDeConfiguracion.getInstance()
				.getIdsVehiculosSimulados(this.nombreSimulacion);
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
				this.archivoSimulacion);
		cargarCoordenadas(new BufferedReader(new InputStreamReader(inputStream)));
		while (true) {
			doSleep();
			inyectarCoordenadasDeMentiritas(idsVehiculosSimulados);
		}
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void cargarCoordenadas(BufferedReader reader) {
		try {
			while (reader.ready()) {
				String coordenadas = reader.readLine();
				String[] split = coordenadas.split(",");
				double lat = Double.parseDouble(split[0]);
				double lng = Double.parseDouble(split[1]);
				Coordenadas c = new Coordenadas(lat, lng);
				this.listaCoordenadas.add(c);
			}
		} catch (IOException e) {
			Logger.getLogger(this.getClass()).error("No se puede leer coordenadas", e);
			throw new RuntimeException(e);
		}
	}

	private void doSleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void inyectarCoordenadasDeMentiritas(List<Integer> idsVehiculosSimulados) {
		int simSize = this.listaCoordenadas.size();
		for (int i = 0; i < idsVehiculosSimulados.size(); i++) {
			Vehiculo v = Central.getInstance().getVehiculoPorId(idsVehiculosSimulados.get(i));
			Coordenadas c = this.listaCoordenadas.get(i % simSize);
			v.nuevoTrack(c.getLatitud(), c.getLongitud());
			// Siempre que modificamos algo del vehiculo tenemos que
			// re-guardarlo para actualizra la DB
			Central.getInstance().addVehiculo(v);
		}
		rotarCordenadasSimuladas();
	}

	/**
	 * Saca la primer coordenada y la pone al final
	 */
	private void rotarCordenadasSimuladas() {
		this.listaCoordenadas.add(this.listaCoordenadas.remove(0));
	}

}
