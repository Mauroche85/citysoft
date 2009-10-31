package ar.edu.utn.frba.proyecto.citysoft.controller.bienvenidaCliente;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class VentanaCliente extends Window {

	public String getTitle() {
		if (UserContext.getUserContext().isUsuarioAutenticado())
			return "Bienvenido " + UserContext.getUserContext().getCliente().getNombre();
		else {
			return "Bienvenido señor anónimo";
		}
	}

}
