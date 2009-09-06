package ar.edu.utn.frba.proyecto.citysoft.controller;

import java.io.IOException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class Login extends Window {

	public void login() throws IOException {
		Textbox usuario = (Textbox) this.getFellow("txtUsuario");
		Textbox password = (Textbox) this.getFellow("txtPassword");
		if (!password.getValue().equals("sex")) {
			throw new RuntimeException("Password inválida (" + password.getValue() + ")");
		}
		UserContext.getUserContext().login(usuario.getValue());
		Executions.getCurrent().sendRedirect("follower.zul");
	}

}
