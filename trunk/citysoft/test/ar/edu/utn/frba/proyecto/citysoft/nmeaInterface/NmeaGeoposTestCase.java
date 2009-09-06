package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

import junit.framework.TestCase;

public class NmeaGeoposTestCase extends TestCase {

	public void testConversionDecimalToDegrees() {
		double lat = -34.628741;
		double lng = -58.458166;

		NmeaGeopos geopos = new NmeaGeopos(lat, lng);
		assertEquals("S  34°37,72'", geopos.getLatInDegMinDec());
		assertEquals("W 058°27,49'", geopos.getLngInDegMinDec());
	}

}
