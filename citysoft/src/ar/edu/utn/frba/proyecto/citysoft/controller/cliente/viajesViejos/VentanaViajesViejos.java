package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.viajesViejos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityVentanaConMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable.ObjetoPlotteable;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class VentanaViajesViejos extends CityVentanaConMapa implements ConstantesHistorialDeViajes {

	private static final long serialVersionUID = 5218186036412956715L;

	// **************************************
	// ** ATRIBUTOS
	// **************************************

	public static final String LISTA_VIAJES_VIEJOS = "listaViajesViejos";

	// **************************************
	// ** INTERFAZ
	// **************************************

	public String getIdMapa() {
		return COMP__GMAP_VIAJES_VIEJOS;
	}

	public List<ObjetoDeDominio> obtenerListaFuenteDe(String nombreLista) {
		if (nombreLista.equals(LISTA_VIAJES_VIEJOS))
			return (List<ObjetoDeDominio>) getViajesViejos();
		else
			throw new RuntimeException("No existe lista fuente para: " + nombreLista);
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private List<ObjetoDeDominio> getViajesViejos() {
		final List<ObjetoDeDominio> viajesViejos = new ArrayList<ObjetoDeDominio>();
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

	// **************************************
	// ** MODELOS ZUL
	// **************************************

	public Set<ObjetoPlotteable> getHistorialDeViajes() {
		return obtenerLista(LISTA_VIAJES_VIEJOS);
	}

}
