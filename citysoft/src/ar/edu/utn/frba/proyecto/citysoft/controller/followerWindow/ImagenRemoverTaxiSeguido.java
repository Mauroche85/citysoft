package ar.edu.utn.frba.proyecto.citysoft.controller.followerWindow;

import org.zkoss.zul.Image;

public class ImagenRemoverTaxiSeguido extends Image {

	private static final long serialVersionUID = 7747229602023248816L;

	public void removerTaxi() {
		FollowerMap gmap = (FollowerMap) getFellow(FollowerWindowComponents.GMAP);
		gmap.quitarGmark(getIdTaxi());
		// Esto es para que se actualice la lista de taxis seguidos
		FollowerWindowUtils.dispararCambiosEnLista(this);
	}

	private int getIdTaxi() {
		ItemDeListaSeguimiento item = (ItemDeListaSeguimiento) this.getParent().getParent();
		return item.getIdTaxi();
	}

}
