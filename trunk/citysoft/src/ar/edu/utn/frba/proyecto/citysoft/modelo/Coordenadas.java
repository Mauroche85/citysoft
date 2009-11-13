package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.text.DecimalFormat;

/**
 * @author Alejandro
 * @version 1.0
 * @created 23-Jul-2009 10:54:51 p.m.
 */
public class Coordenadas implements ObjetoDeDominio {

	public Coordenadas(double lat, double lng) {
		this.latitud = lat;
		this.longitud = lng;
	}

	// **************************************
	// ** Attributes
	// **************************************

	private double latitud;
	private double longitud;

	// **************************************
	// ** Accessors
	// **************************************

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	// **************************************
	// ** Execution
	// **************************************

	/**
	 * Devuelve por ejemplo: S 34°37,72'
	 */
	public String getLatInDegMinDec() {
		int degree = (int) latitud;
		String sgn = (degree >= 0) ? "N" : "S";
		double minutes = Math.abs(latitud - degree);
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
		int degree = (int) longitud;
		String sgn = (degree >= 0) ? "E" : "W";
		double minutes = Math.abs(longitud - degree);
		double hexMin = 100.0 * minutes * (6.0 / 10.0);
		DecimalFormat df = new DecimalFormat("00.00");
		String strMinutes = df.format(hexMin);
		df = new DecimalFormat("000");
		return sgn + " " + df.format((long) Math.abs(degree)) + "°" + strMinutes + "'";
	}

	// **************************************
	// ** Interfaces
	// **************************************

	@Override
	public int compareTo(ObjetoDeDominio theOther) {
		Coordenadas o = (Coordenadas) theOther;
		Double d = new Double(this.latitud + this.longitud);
		Double d2 = new Double(o.latitud + o.longitud);
		return d.compareTo(d2);
	}

	public String toString() {
		return "Localización geográfica: latitud " + this.getLatInDegMinDec() + ", longitud "
				+ this.getLngInDegMinDec();
	}

}