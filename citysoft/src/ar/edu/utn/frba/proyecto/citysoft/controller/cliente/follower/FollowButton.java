package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.follower;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;

public class FollowButton extends Button implements FollowerWindowComponents {

	private static final long serialVersionUID = -7906814153393230294L;

	public void seguir() {
		Combobox cmbIdViaje = (Combobox) getFellow(CMB_ID_VIAJE);
		int idViaje = (Integer) cmbIdViaje.getSelectedItem().getValue();
		// Agrego la GMARK
		Viaje viaje = Central.getInstance().getViaje(idViaje);
		((FollowerMap) getFellow(GMAP)).agregarGmark(viaje.getVehiculo());
		// Borro la casilla donde el usuario me puso el nro de viaje a seguir
		cmbIdViaje.setValue(null);
		// Esto es para que se actualice la lista de vehiculos seguidos
		FollowerWindowUtils.dispararCambiosEnLista(this);
	}

}
