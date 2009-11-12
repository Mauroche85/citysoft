package ar.edu.utn.frba.proyecto.citysoft.controller.cliente;

import org.zkoss.zul.Row;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class ManipularComboUsuario extends Row implements ConstantesGeneralesDeVentanas{
	public void cargarUsuario(){
	System.out.println("Cargar Usuario"); 
	String vUsuario = null;


	if (!(UserContext.getUserContext().isUsuarioOperador())){
			Row cbUsuario = (Row) this.getFellow("filaCliente");
			cbUsuario.setVisible(false);
		}
	}
}
