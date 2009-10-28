package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class ReceptorNmea implements Runnable {

	private static final String MONITOR_HOSTNAME = "localhost";
	private static final int MONITOR_PORT = 2221;

	// **************************************
	// ** Execution
	// **************************************

	/**
	 * Empieza a leer actualizaciones de la información de localización
	 */
	@Override
	public void run() {
		Socket socket;
		InputStream inputStream;
		BufferedReader reader;
		try {
			socket = new Socket(MONITOR_HOSTNAME, MONITOR_PORT);
			inputStream = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
		} catch (IOException e) { // y UnknownHostException
			Logger.getLogger(this.getClass()).fatal("No se puede leer sentencia NMEA", e);
			throw new RuntimeException(e);
		}

		while (true) {
			String nmea = leer(reader);
			hacer(nmea);
		}
	}

	private String leer(BufferedReader reader) {
		String nmea = "";
		try {
			// TODO leer byte por byte
			nmea = reader.readLine();
		} catch (IOException e) {
			Logger.getLogger(this.getClass()).error("No se puede leer sentencia NMEA", e);
			throw new RuntimeException(e);
		}
		return nmea;
	}

	private void hacer(String nmea) {
		Logger.getLogger(this.getClass()).debug("Sentencia NMEA recibida: " + nmea);
		NmeaGeopos pos = NmeaGeoposParser.parse(nmea);
		// Le hardcodeamos la posicion a JUT
		Vehiculo v = Central.getInstance().getVehiculoPorTrackerId("TAXI01");
		v.nuevoTrack(pos.lat, pos.lng);
		Central.getInstance().addVehiculo(v);
		// Le hardcodeamos una posicion vecina a Lemiaux
		v = Central.getInstance().getVehiculoPorTrackerId("TAXI02");
		v.nuevoTrack(pos.lat, pos.lng - 0.01);
		Central.getInstance().addVehiculo(v);
	}

}
