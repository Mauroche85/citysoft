package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteable;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteableFactory;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;

/**
 * Esta clase es una ventana que sirve de nexo entre el mapa y una o mas listas
 * registradas de objetos plotteables. Un objeto plotteable, es un objeto que
 * tiene ciertos aspectos que pueden ser dibujados en un mapa (ver
 * {@link ObjetoPlotteable}).
 * 
 * Se diferencia de {@link Window} en tres aspectos principales: (1) tiene un
 * mapa bien definido; (2) tiene una o mas listas etiquetadas, cuyos items son
 * objetos ploteables; (3) un objeto plotteable tiene la capacidad de mostrarse
 * y ocultarse en el mapa segun la interacción del usuario; (4) el mapa puede
 * mantenerse centrado en alguno de estos objetos ploteables; y (5) se puede
 * pedir el refresco del mapa en relacion a los objetos ploteables de una de las
 * listas etiquetadas registradas.
 * 
 * Importante: el mapa de listas etiquetadas está pensado para que cada lista
 * sea el objeto de un databinding. Así, el actor del listbox obra directamente
 * sobre la lista etiquetada. Esto significa, que cada subclase debe generar su
 * propio mecanismo de sincronizacion de cada lista etiquetada con la colección
 * que realmente representa.
 * 
 * Mas importante: el refresco del mapa se realiza en realidad pidiendole a cada
 * elemento de la lista a refrecar, que se autorefresque. Esto significa, que
 * cada objeto ploteable tiene la capacidad de refrescar sus marcas y lo que sea
 * en funcion del objeto de negocio que hay detras
 * 
 * 
 * INTERFAZ:
 * 
 * <li>registrar nueva lista de objetos ploteables:
 * {@link #registrarLista(String, List)}
 * 
 * <li>obtener lista de objetos ploteables xxx: {@link #obtenerLista(String)}
 * 
 * <li>actualizar en el mapa la lista xxx:
 * {@link #actualizarPlottingDeLista(String)}
 * 
 * 
 * @see CityMapa
 * @see CityMarcador
 */
public abstract class CityVentanaConMapa extends Window implements ICityVentanaConMapa {

	private static final long serialVersionUID = -2935997761934253373L;

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	private Map<String, Set<ObjetoPlotteable>> listas;
	private Set<String> listasQueCambiaron;
	private CityMapa mapa;

	// **************************************
	// ** ELEMENTOS
	// **************************************

	public CityMapa elemMapa() {
		if (this.mapa == null) {
			this.mapa = (CityMapa) getFellow(getIdMapa());
		}
		return this.mapa;
	}

	// **************************************
	// ** INTERFAZ - GRAL
	// **************************************

	public abstract String getIdMapa();

	// **************************************
	// ** INTERFAZ - LISTAS
	// **************************************

	public Set<ObjetoPlotteable> obtenerLista(String nombreLista) {
		Set<ObjetoPlotteable> lista = getListas().get(nombreLista);
		if (lista == null) {
			lista = new TreeSet<ObjetoPlotteable>();
			getListas().put(nombreLista, lista);
		}
		return lista;
	}

	public abstract List<ObjetoDeDominio> obtenerListaFuenteDe(String nombreLista);

	public void sincronizarLista(String nombreLista) {
		Set<ObjetoPlotteable> listaDeLaVentana = obtenerLista(nombreLista);
		Collection<ObjetoDeDominio> listaFuente = obtenerListaFuenteDe(nombreLista);

		// Quitamos los objetos de dominio que ya no tienen que estar en la
		// lista del usuario (porque se borraron del backend)
		List<ObjetoPlotteable> aRemover = new ArrayList<ObjetoPlotteable>();
		for (ObjetoPlotteable objetoPlotteable : listaDeLaVentana) {
			if (!listaFuente.contains(objetoPlotteable.getObjetoDelModelo())) {
				// Aca no se puede hacer:
				// > listaDeLaVentana.remove(objetoPlotteable);
				// porque tira concurrent modification exception (ya q estamos
				// iterandolo)
				aRemover.add(objetoPlotteable);
				getListasQueCambiaron().add(nombreLista);
			} else {
				listaFuente.remove(objetoPlotteable.getObjetoDelModelo());
			}
		}
		for (ObjetoPlotteable ploteable : aRemover) {
			if (ploteable.estoyPloteado())
				ploteable.quitarDelMapa();
			listaDeLaVentana.remove(ploteable);
		}
		// Ahora, en listaFuente solo tenemos los nuevos objetos de dominio (ya
		// que aquellos que ya estaban siendo mostrados en la lista del usuario,
		// los fuimos quitando de esta listaFuente)
		for (ObjetoDeDominio d : listaFuente) {
			ObjetoPlotteable objetoPlotteable = ObjetoPlotteableFactory.crearObjetoPloteable(d);
			listaDeLaVentana.add(objetoPlotteable);
			getListasQueCambiaron().add(nombreLista);
		}
	}

	public void sincronizarListas() {
		for (String listName : this.listas.keySet()) {
			sincronizarLista(listName);
		}
	}

	// **************************************
	// ** INTERFAZ - PLOTTING
	// **************************************

	/**
	 * Dada la lista, recorre sus objetos para que se repinten (o sea, que
	 * actualicen su posicion y esas cosas)
	 */
	public void actualizarPlottingDeLista(String nombreLista) {
		Set<ObjetoPlotteable> lista = obtenerLista(nombreLista);
		for (ObjetoPlotteable objetoPlotteable : lista) {
			if (objetoPlotteable.estoyPloteado())
				objetoPlotteable.actualizarPlotting();
		}
	}

	public void actualizarPlottings() {
		for (String listName : this.listas.keySet()) {
			actualizarPlottingDeLista(listName);
		}
		elemMapa().actualizarPosicionCentral();
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private Map<String, Set<ObjetoPlotteable>> getListas() {
		if (this.listas == null) {
			this.listas = new TreeMap<String, Set<ObjetoPlotteable>>();
		}
		return this.listas;
	}

	protected Set<String> getListasQueCambiaron() {
		if (this.listasQueCambiaron == null) {
			this.listasQueCambiaron = new HashSet<String>();
		}
		return this.listasQueCambiaron;
	}
}
