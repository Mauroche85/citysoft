package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft;

import java.util.List;
import java.util.Set;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteable;
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
 * y ocultarse en el mapa segun la interacci�n del usuario; (4) el mapa puede
 * mantenerse centrado en alguno de estos objetos ploteables; y (5) se puede
 * pedir el refresco del mapa en relacion a los objetos ploteables de una de las
 * listas etiquetadas registradas.
 * 
 * Importante: el mapa de listas etiquetadas est� pensado para que cada lista
 * sea el objeto de un databinding. As�, el actor del listbox obra directamente
 * sobre la lista etiquetada. Esto significa, que cada subclase debe generar su
 * propio mecanismo de sincronizacion de cada lista etiquetada con la colecci�n
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
public interface ICityVentanaConMapa {

	// **************************************
	// ** INTERFAZ
	// **************************************

	abstract String getIdMapa();

	// **************************************
	// ** INTERFAZ - LISTAS
	// **************************************

	Set<ObjetoPlotteable> obtenerLista(String nombreLista);

	List<ObjetoDeDominio> obtenerListaFuenteDe(String nombreLista);

	void sincronizarLista(String nombreLista);

	void sincronizarListas();

	// **************************************
	// ** INTERFAZ - PLOTTING
	// **************************************

	void actualizarPlottingDeLista(String nombreLista);

	void actualizarPlottings();

}
