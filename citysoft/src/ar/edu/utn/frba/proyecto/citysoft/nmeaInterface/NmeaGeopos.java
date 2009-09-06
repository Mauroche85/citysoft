package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

import java.text.DecimalFormat;

public class NmeaGeopos {

	private static final String COORD_DECIMAL_SEPARATOR = ".";

	// **************************************
	// ** Constructors
	// **************************************

	public NmeaGeopos(double l, double g) {
		this.lat = l;
		this.lng = g;
	}

	/**
	 * Me viene algo del estilo...
	 * 
	 * @param lat
	 *            4916.45, equivalentes a 49° y 16,45'
	 * @param latSign
	 *            : S (negativo) o N (positivo)
	 * @param lng
	 *            : 12311.12, equivalentes a 123° y 11,12'
	 * @param lngSign
	 *            : W (negativo) o E (positivo)
	 */
	public NmeaGeopos(String lat, String latSign, String lng, String lngSign) {
		// *** LATITUD **********************++
		// Lat: 2345.1234 >> 23° 25,1234 >> 23,xxx (donde xxx es la repres dec)
		int indexOfMinutes = lat.indexOf(COORD_DECIMAL_SEPARATOR) - 2;
		String strLatDegrees = lat.substring(0, indexOfMinutes);
		String strLatMinutes = lat.substring(indexOfMinutes);
		//
		int latDegrees = Integer.parseInt(strLatDegrees);
		double latMinutes = Double.parseDouble(strLatMinutes);
		// Pasamos los minutos a un sistema decimal
		double latMinutesToDecimal = (latMinutes * 100 / 60) / 100;
		// Hacemos el calculo finale
		if (latSign.equals("N"))
			this.lat = latDegrees + latMinutesToDecimal;
		else if (latSign.equals("S"))
			this.lat = -1 * (latDegrees + latMinutesToDecimal);
		else
			throw new RuntimeException("No se reconoce la latitud: " + this.lat + " " + latSign);

		// *** LONGITUD **********************++
		// Lng: 01234.1234 >> 12° 34,1234 >> 34,xxx (donde xxx es la repres dec)
		indexOfMinutes = lng.indexOf(COORD_DECIMAL_SEPARATOR) - 2;
		String strLngDegrees = lng.substring(0, indexOfMinutes);
		String strLngMinutes = lng.substring(indexOfMinutes);
		//
		int lngDegrees = Integer.parseInt(strLngDegrees);
		double lngMinutes = Double.parseDouble(strLngMinutes);
		// Pasamos los minutos a un sistema decimal
		double lngMinutesToDecimal = (lngMinutes * 100 / 60) / 100;
		// Hacemos el calculo finale
		if (lngSign.equals("E"))
			this.lng = lngDegrees + lngMinutesToDecimal;
		else if (lngSign.equals("W"))
			this.lng = -1 * (lngDegrees + lngMinutesToDecimal);
		else
			throw new RuntimeException("No se reconoce la longitud: " + this.lng + " " + lngSign);
	}

	// **************************************
	// ** Attributes
	// **************************************

	public double lat = 0.0;
	public double lng = 0.0;

	// **************************************
	// ** Accessors
	// **************************************

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	// **************************************
	// ** Execution
	// **************************************

	/**
	 * Devuelve por ejemplo: S 34°37,72'
	 */
	public String getLatInDegMinDec() {
		int degree = (int) lat;
		String sgn = (degree >= 0) ? "N" : "S";
		double minutes = Math.abs(lat - degree);
		double hexMin = 100.0 * minutes * (6.0 / 10.0);
		DecimalFormat df = new DecimalFormat("00.00");
		String strMinutes = df.format(hexMin);
		df = new DecimalFormat("00");
		return sgn + "  " + df.format((long) Math.abs(degree)) + "°" + strMinutes + "'";
	}

	/**
	 * Devuelve por ejemplo: W 058°27,49'
	 */
	public String getLngInDegMinDec() {
		int degree = (int) lng;
		String sgn = (degree >= 0) ? "E" : "W";
		double minutes = Math.abs(lng - degree);
		double hexMin = 100.0 * minutes * (6.0 / 10.0);
		DecimalFormat df = new DecimalFormat("00.00");
		String strMinutes = df.format(hexMin);
		df = new DecimalFormat("000");
		return sgn + " " + df.format((long) Math.abs(degree)) + "°" + strMinutes + "'";
	}

	// **************************************
	// ** Helpers
	// **************************************

	public String toString() {
		return "Localización geográfica: latitud " + this.getLatInDegMinDec() + ", longitud "
				+ this.getLngInDegMinDec();
	}

}
