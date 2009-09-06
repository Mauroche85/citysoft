package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

public class NmeaGeoposParser {

	private static final String NMEA_SENTENCE_SEPARATOR = ",";
	private static final String DATATYPE_RMC = "RMC";
	private static final String DATATYPE_GLL = "GLL";
	private static final String DATATYPE_GGA = "GGA";

	// **************************************
	// ** Execution
	// **************************************

	static public boolean isParseable(String nmea) {
		return isGGA(nmea) || isGLL(nmea) || isRMC(nmea);
	}

	static public NmeaGeopos parse(String nmea) {
		if (isParseable(nmea)) {
			if (isGGA(nmea))
				return parseGGA(nmea);
			else if (isGLL(nmea))
				return parseGLL(nmea);
			else if (isRMC(nmea))
				return parseRMC(nmea);
			else
				throw new RuntimeException();
		} else
			throw new RuntimeException("No se reconoce la sentencia: " + nmea);
	}

	// **************************************
	// ** Parsers posta posta
	// **************************************

	static private NmeaGeopos parseGGA(String nmea) {
		// $GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
		// _____________ `--lat---´ `---lng---´

		String[] comps = nmea.split(NMEA_SENTENCE_SEPARATOR);

		String lat = comps[2]; // 4807.038 (lat)
		String latSig = comps[3]; // N (lat)
		String lng = comps[4]; // 01131.000 (lng)
		String lngSig = comps[5]; // E (lng)
		return new NmeaGeopos(lat, latSig, lng, lngSig);
	}

	static private NmeaGeopos parseGLL(String nmea) {
		// $GPGLL,4916.45,N,12311.12,W,225444,A,*1D
		// ______ `--lat--´ `--lng---´

		String[] comps = nmea.split(NMEA_SENTENCE_SEPARATOR);

		String lat = comps[1];
		String latSig = comps[2];
		String lng = comps[3];
		String lngSig = comps[4];
		return new NmeaGeopos(lat, latSig, lng, lngSig);
	}

	static private NmeaGeopos parseRMC(String nmea) {
		// $GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A
		// _______________ `--lat---´ `---lng---´

		String[] comps = nmea.split(NMEA_SENTENCE_SEPARATOR);

		String lat = comps[3];
		String latSig = comps[4];
		String lng = comps[5];
		String lngSig = comps[6];
		return new NmeaGeopos(lat, latSig, lng, lngSig);
	}

	// **************************************
	// ** Helpers
	// **************************************

	static private boolean isGGA(String nmea) {
		return getDatatype(nmea).equals(DATATYPE_GGA);
	}

	static private boolean isGLL(String nmea) {
		return getDatatype(nmea).equals(DATATYPE_GLL);
	}

	static private boolean isRMC(String nmea) {
		return getDatatype(nmea).equals(DATATYPE_RMC);
	}

	static public String getDatatype(String nmea) {
		return nmea.substring(3, 6);
	}

}
