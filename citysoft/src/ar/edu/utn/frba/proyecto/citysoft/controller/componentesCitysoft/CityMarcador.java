package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import org.zkoss.gmaps.Gmarker;

/**
 * Es un marker para el mapa de google, pero customizado. Un mapa puede tener
 * uno de estos como centro. En tal caso, quien centra al mapa no es el mismo
 * mapa, sino el marcador. O sea que cuando el marcador cambie, fuerza tambien
 * el recentrado del mapa.
 * 
 * El marcador no sabe si es centro de un determinado mapa, sino que es el mapa
 * en si mismo quien sabe que marcador lo centra. Esto es por varios motivos,
 * entre ellos: evitar malos algoritmos que se descuiden y puedan generar varias
 * marcas que crean ser centros; eficiencia cuando el mapa quiere recentrarse de
 * forma que no vaya preguntando marca por marca, etc.
 * 
 * INTERFAZ:
 * 
 * <li>tu mapa sera...: {@link #setMapa(CityMapa)}
 * 
 * <li>ser�s el centro de tu mapa: {@link #serasCentro(CityMapa)}
 * 
 * <li>tendras estas nuevas coordenadas (y si eres centro, refrescaras el mapa):
 * {@link #setLat(double)}, {@link #setLng(double)}
 * 
 * 
 * @see CityMapa
 * @see CityMarcador
 */
public class CityMarcador extends Gmarker {

	// **************************************
	// ** INTERFAZ
	// **************************************

	@Override
	public void setLat(double lat) {
		super.setLat(lat);
		if (tengoMapa() && soyCentroDelMapa()) {
			getMapa().setLat(lat);
		}
	}

	@Override
	public void setLng(double lng) {
		super.setLng(lng);
		if (tengoMapa() && soyCentroDelMapa()) {
			getMapa().setLng(lng);
		}
	}

	// **************************************
	// ** ESPECIALIZAZCION
	// **************************************

	private boolean isDirty;

	@Override
	public void detach() {
		super.detach();
		this.isDirty = true;
	}

	public boolean isDirty() {
		return this.isDirty;
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private CityMapa getMapa() {
		return (CityMapa) this.getParent();
	}

	private boolean tengoMapa() {
		return getMapa() != null;
	}

	private boolean soyCentroDelMapa() {
		return this.equals(getMapa().quienMeCentra());
	}

}