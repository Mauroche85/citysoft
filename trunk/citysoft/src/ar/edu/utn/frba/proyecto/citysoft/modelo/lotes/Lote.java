package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes;

import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;

/**
 * Un lote es un conjunto de objetos que sirve para, por ejemplo, configurar la
 * aplicaci�n inicial.
 * 
 * Ejempo de uso 1: Cuando la versi�n final de una aplicaci�n es puesta a correr
 * de posta, debe tener un conjunto de datos cargados: la parametrizaci�n de la
 * base. Entonces, en lugar de tener una base de datos inicial para cuando
 * querramos instalar la aplicaci�n "en el cliente" o en donde sea, directamente
 * tenemos un lote, y DICHO lote ser� el que parametrice la base de datos.
 * 
 * Ejemplo de uso 2: Cuando estamos por testear la aplicaci�n, vamos a querer
 * tener lotes de pruebas. Algunos lotes son generales para todas las pruebas,
 * algunos aplican s�lo a un conjunto.
 * 
 * Ejemplo de uso 3: Cuando la aplicaci�n est� en pleno desarrollo, tal vez no
 * queremos usar una DB, porque es lenta y tenemos que perder tiempo
 * configurando un framework de persistencia. Entonces, decidimos que es mejor
 * testear la aplicaci�n con todos sus objetos directamente en memoria, y cuando
 * inicia la aplicaci�n subimos a memoria todos estos objetos. Este conjunto de
 * objetos con el que se trabajar� en desarrollo, puede ser definido en uno o
 * varios lotes tambi�n. Podes usar una subclase de lote por cada objeto de
 * dominio (LoteDeTaxis, LoteDeClientes, etc) o podes usar un lote universal
 * (LoteDeAplicacion).
 * 
 * @author Alejandro
 */
public interface Lote {

	/**
	 * Devuelve una lista de elementos
	 */
	List<ObjetoDeDominio> getLote();

	/**
	 * Carga en la central de taxis la lista de elementos en sus colecciones
	 * correspondientes
	 */
	public void cargar();

}
