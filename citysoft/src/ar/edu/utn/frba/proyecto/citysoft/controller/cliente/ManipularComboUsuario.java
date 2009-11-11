package ar.edu.utn.frba.proyecto.citysoft.controller.cliente;

import org.zkoss.zul.Row;
import org.zkoss.zul.Window;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.api.Textbox;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class ManipularComboUsuario extends Row implements ConstantesGeneralesDeVentanas{
	public void cargarUsuario(){
	System.out.println("Cargar Usuario"); 
	String vUsuario = null;

	if (UserContext.getUserContext().isUsuarioAutenticado())
		vUsuario= UserContext.getUserContext().getCliente().getNombre();
	System.out.println("Usuario:" +vUsuario);	
	if (!(UserContext.getUserContext().isUsuarioOperador())){
			Row cbUsuario = (Row) this.getFellow("filaCliente");
			cbUsuario.setVisible(false);
		}
	}
}
