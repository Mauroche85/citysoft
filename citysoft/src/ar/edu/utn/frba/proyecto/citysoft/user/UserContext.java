package ar.edu.utn.frba.proyecto.citysoft.user;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

/**
 * Cuando un usuario entra por primera vez a una pagina, se genera un contexto
 * de usuario anónimo. El usuario, a través de la página de login puede
 * autenticarse. Una vez que el usuario se autentique, el contexto de usuario
 * pasará a ser el de un usuario autenticado con un cliente asociado. Pero
 * mientras tanto, el usuario permanecera anonimo y no podrá entrar a las
 * paginas /secure/*
 */
public class UserContext {

	private static final String USER_ANONYMOUS = "(Anónimo)";

	private static ThreadLocal<UserContext> tlUserContext = new ThreadLocal<UserContext>();

	/**
	 * Se usa para recuperar el contexto de usuario. Esto es, una vez que el
	 * usuario ya está logueado, los sucesivos pedidos son interceptados por un
	 * filtro que carga el contexto de usuario según el generado al momento de
	 * realizar el login, para que así en cualquier momento esté disponible
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

	private Cliente cliente;

	// **************************************
	// ** Accessors
	// **************************************

	public String getUsername() {
		return (cliente != null) ? cliente.getNombreUsuario() : USER_ANONYMOUS;
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
		return this.cliente != null;
	}

	// TODO: ver si este metodo devuelve autenticable. Ver tambien si el login
	// setea el cliente, un objeto autenticable, o un cliente/operador segun
	// quien se haya logueado
	public Autenticable login(String usuario, String password) {
		if (this.isUsuarioAutenticado()) {
			throw new RuntimeException("Ya existe sesión (" + this.getUsername() + ")");
		} else {
			Cliente c = CentralTaxis.getInstance().getCliente(usuario);
			if (c == null) {
				throw new RuntimeException("Usuario inválido (" + usuario + ")");
			} else if (!password.equals(c.getPassword())) {
				throw new RuntimeException("Password inválida (" + password + ")");
			} else {
				this.cliente = c;
				return c;
			}
		}
	}

}
