package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.viajesViejos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityVentanaConMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteable;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.PloteableDesdeViaje;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class VentanaViajesViejos extends CityVentanaConMapa implements ConstantesHistorialDeViajes {

	private static final String LISTA_VIAJES_VIEJOS = "listaViajesViejos";

	// **************************************
	// ** INTERFAZ
	// **************************************

	public String getIdMapa() {
		return COMP__GMAP_VIAJES_VIEJOS;
	}

	// **************************************
	// ** CONSTRUCTOR
	// **************************************

	public VentanaViajesViejos() {
		registrarLista(LISTA_VIAJES_VIEJOS, new ArrayList<ObjetoPlotteable>());
		sincronizarListaViajesViejos();
	}

	// **************************************
	// ** INTERFAZ PARA ZUL
	// **************************************

	public Collection<ObjetoPlotteable> getHistorialDeViajes() {
		return obtenerLista(LISTA_VIAJES_VIEJOS);
	}

	// **************************************
	// ** SINCRONIZACION
	// **************************************

	public void sincronizarListaViajesViejos() {
		List<ObjetoPlotteable> listaDeLaVentana = obtenerLista(LISTA_VIAJES_VIEJOS);
		Collection<Viaje> viajesViejos = getViajesViejos();

		// Quitamos los viajes que ya no deben estar en la lista del usuario
		for (ObjetoPlotteable objetoPlotteable : listaDeLaVentana) {
			if (!viajesViejos.contains(objetoPlotteable.getObjetoDelModelo())) {
				objetoPlotteable.quitarMarcadoresDelMapa();
				listaDeLaVentana.remove(objetoPlotteable);
			} else {
				viajesViejos.remove(objetoPlotteable.getObjetoDelModelo());
			}
		}
		// Ahora, el viajesViejos solo tenemos los nuevos (ya que los repetidos
		// los ibamos quitabamos)
		for (Viaje v : viajesViejos) {
			ObjetoPlotteable objetoPlotteable = new PloteableDesdeViaje(v);
			listaDeLaVentana.add(objetoPlotteable);
		}
	}

	private Collection<Viaje> getViajesViejos() {
		final Collection<Viaje> viajesViejos = new ArrayList<Viaje>();
		CollectionUtils.forAllDo(Central.getInstance().getViajes(), new Closure() {
			@Override
			public void execute(Object arg0) {
				Viaje v = (Viaje) arg0;
				// Si el viaje le pertenece al usuario conectado
				if ((v.fueCompletado())
						&& v.getCliente().equals(UserContext.getUserContext().getCliente())) {
					viajesViejos.add(v);
				}
			}
		});
		return viajesViejos;
	}

}
