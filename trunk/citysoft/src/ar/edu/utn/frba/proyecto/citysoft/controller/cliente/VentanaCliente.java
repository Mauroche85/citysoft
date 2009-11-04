package ar.edu.utn.frba.proyecto.citysoft.controller.cliente;

import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class VentanaCliente extends Window implements ConstantesGeneralesDeVentanas {

	public String getTitle() {
		if (UserContext.getUserContext().isUsuarioAutenticado())
			return "Bienvenido " + UserContext.getUserContext().getCliente().getNombre();
		else {
			return "Bienvenido señor anónimo";
		}
	}

}
