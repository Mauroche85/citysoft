package ar.edu.utn.frba.proyecto.citysoft.controller.followerWindow;

import org.zkoss.zul.Image;

public class ImagenRemoverVehiculoSeguido extends Image {

	private static final long serialVersionUID = 7747229602023248816L;

	public void removerVehiculo() {
		FollowerMap gmap = (FollowerMap) getFellow(FollowerWindowComponents.GMAP);
		gmap.quitarGmark(getPatente());
		// Esto es para que se actualice la lista de vehiculos seguidos
		FollowerWindowUtils.dispararCambiosEnLista(this);
	}

	private String getPatente() {
		ItemDeListaSeguimiento item = (ItemDeListaSeguimiento) this.getParent().getParent();
		return (String) item.getValue();
	}

}
