package ar.edu.utn.frba.proyecto.citysoft.user;

import ar.edu.utn.frba.proyecto.citysoft.modelo.CentralTaxis;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;

public class UserContext {

	private static ThreadLocal<UserContext> tlUserContext = new ThreadLocal<UserContext>();

	public static void setUserContext(UserContext uc) {
		tlUserContext.set(uc);
	}

	public static UserContext getUserContext() {
		UserContext userContext = tlUserContext.get();
		if (userContext == null) {
			userContext = new UserContext();
			tlUserContext.set(userContext);
		}
		return userContext;
	}

	// **************************************
	// ** Attributes
	// **************************************

	private String username;
	private Cliente cliente;

	// **************************************
	// ** Accessors
	// **************************************

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// **************************************
	// ** Execution
	// **************************************

	public boolean isUsuarioAutenticado() {
		return getCliente() != null;
	}

	public void login(String usuario) {
		this.username = usuario;
		this.cliente = CentralTaxis.getInstance().getCliente(usuario);
		if (this.cliente == null) {
			throw new RuntimeException("Usuario inválido (" + usuario + ")");
		}
	}

}
