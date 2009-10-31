package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.follower;

import org.zkoss.zul.Timer;

public class FollowerTimer extends Timer implements FollowerWindowComponents {

	private static final long serialVersionUID = 5608357073418728915L;

	private FollowerMap fmap;

	// **************************************
	// ** Execution
	// **************************************

	public void ring() {
		if (this.fmap == null) {
			this.fmap = (FollowerMap) getFellow(GMAP);
		}
		this.fmap.actualizarVista();
	}

}
