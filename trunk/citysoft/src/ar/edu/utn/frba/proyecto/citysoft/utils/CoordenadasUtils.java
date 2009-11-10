package ar.edu.utn.frba.proyecto.citysoft.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Coordenadas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class CoordenadasUtils {

	public static void generarTracksParaViaje(Viaje v, String archivoDeTracksSerializados,
			Date generarTracksAPartirDeLas) {
		final long FIVE_SECS = Long.parseLong("5000");
		Date horaDelTrack = new Date(generarTracksAPartirDeLas.getTime());
		List<Coordenadas> listaCoordenadas = leerCoordenadas(archivoDeTracksSerializados);
		for (Coordenadas c : listaCoordenadas) {
			v.getVehiculo().nuevoTrack(c.getLatitud(), c.getLongitud(), horaDelTrack);
			// Copiamos la hora, y le agregamos 5 segundos
			horaDelTrack = new Date(horaDelTrack.getTime() + FIVE_SECS);
		}
	}

	public static List<Coordenadas> leerCoordenadas(String filename) {
		List<Coordenadas> list = new ArrayList<Coordenadas>();
		InputStream inputStream = CoordenadasUtils.class.getClassLoader().getResourceAsStream(
				filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while (reader.ready()) {
				String coordenadas = reader.readLine();
				String[] split = coordenadas.split(",");
				double lat = Double.parseDouble(split[0]);
				double lng = Double.parseDouble(split[1]);
				Coordenadas c = new Coordenadas(lat, lng);
				list.add(c);
			}
		} catch (IOException e) {
			Logger.getLogger(CoordenadasUtils.class).error("No se puede leer coordenadas", e);
			throw new RuntimeException(e);
		}
		return list;
	}

}
