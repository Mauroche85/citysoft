package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

/**
 * Cuando un usuario entra por primera vez a una pagina, se genera un contexto
 * de usuario an�nimo. El usuario, a trav�s de la p�gina de login puede
 * autenticarse. Una vez que el usuario se autentique, el contexto de usuario
 * pasar� a ser el de un usuario autenticado con un cliente asociado. Pero
 * mientras tanto, el usuario permanecera anonimo y no podr� entrar a las
 * paginas /secure/*
 */
public class UserContext {

	private static final String USER_ANONYMOUS__DISPLAY_NAME = "(An�nimo)";
	private static final String USER_OPERADOR = "operador";
	private static final String PASS_OPERADOR = "operador";

	private static ThreadLocal<UserContext> tlUserContext = new ThreadLocal<UserContext>();

	/**
	 * Se usa para recuperar el contexto de usuario. Esto es, una vez que el
	 * usuario ya est� logueado, los sucesivos pedidos son interceptados por un
	 * filtro que carga el contexto de usuario seg�n el generado al momento de
	 * realizar el login, para que as� en cualquier momento est� disponible
	 * (dentro del ambiente de los componentes ZK. En realidad, dentro del
	 * ambiente Servlet).
	 */
	public static void setUserContext(UserContext uc) {
		tlUserContext.set(uc);
	}

	public static UserContext getUserContext() {
		return tlUserContext.get();
	}

	// **************************************
	// ** Attributes
	// **************************************

	private boolean usuarioAutenticado;
	private boolean usuarioOperador;
	private Cliente cliente;

	// **************************************
	// ** Accessors
	// **************************************

	public String getUsername() {
		return (cliente != null) ? cliente.getNombreUsuario() : USER_ANONYMOUS__DISPLAY_NAME;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// **************************************
	// ** Execution
	// **************************************

	public boolean isUsuarioAutenticado() {
		return this.usuarioAutenticado;
	}

	public boolean isUsuarioOperador() {
		return this.usuarioOperador;
	}

	public void login(String usuario, String password) {
		if (this.isUsuarioAutenticado()) {
			throw new RuntimeException("Ya existe sesi�n (" + this.getUsername() + ")");
		} else {
			if (usuario.equals(USER_OPERADOR)) {
				if (password.equals(PASS_OPERADOR)) {
					this.usuarioAutenticado = true;
					this.usuarioOperador = true;
				} else {
					throw new RuntimeException("Password inv�lida (" + password + ")");
				}
			} else {
				Cliente c = Central.getInstance().getCliente(usuario);
				if (c == null) {
					throw new RuntimeException("Usuario inv�lido (" + usuario + ")");
				} else if (!password.equals(c.getPassword())) {
					throw new RuntimeException("Password inv�lida (" + password + ")");
				} else {
					this.usuarioAutenticado = true;
					this.cliente = c;
				}
			}
		}
	}

	public void logout() {
		this.cliente = null;
		this.usuarioAutenticado = false;
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect(ConstantesGeneralesDeVentanas.ZUL__LOGIN);
	}

}
