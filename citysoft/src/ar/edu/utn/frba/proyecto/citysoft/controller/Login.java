package ar.edu.utn.frba.proyecto.citysoft.controller;

import java.io.IOException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class Login extends Window implements ConstantesGeneralesDeVentanas {

	public void onLoad() {
		if (UserContext.getUserContext().isUsuarioAutenticado()) {
			getFellow("boxLogin").setVisible(false);
			getFellow("boxLogout").setVisible(true);
		}
	}

	public void login(String forwardUri) throws IOException {
		Textbox txtUsuario = (Textbox) this.getFellow("txtUsuario");
		Textbox txtPassword = (Textbox) this.getFellow("txtPassword");
		UserContext.getUserContext().login(txtUsuario.getValue(), txtPassword.getValue());

		// Si nos pidieron alguna URL particular, vamos hacia donde venimos.
		// Sino, a bienvenido.zul
		if (forwardUri != null && !forwardUri.isEmpty()) {
			String contextPath = Executions.getCurrent().getContextPath();
			if (forwardUri.startsWith(contextPath))
				forwardUri = forwardUri.substring(contextPath.length());
		} else {
			forwardUri = ZUL__BIENVENIDO;
		}
		Executions.getCurrent().sendRedirect(forwardUri);
	}

	public void logout() {
		UserContext.getUserContext().logout();
	}

}
