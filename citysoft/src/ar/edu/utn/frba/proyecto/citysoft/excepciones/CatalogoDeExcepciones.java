package ar.edu.utn.frba.proyecto.citysoft.excepciones;

public class CatalogoDeExcepciones {

	private CatalogoDeExcepciones() {
	}

	private static final CatalogoDeExcepciones thiz = new CatalogoDeExcepciones();

	public static CatalogoDeExcepciones getInstance() {
		return thiz;
	}

	// ***************************************
	// ** Catalogo
	// ***************************************

	public RuntimeException romperTodo() {
		return new ExcepcionDelServidor("Operacion no implementada");
	}

	/**
	 * Para tirar excepciones del tipo IOExceptions que no pueden ser throweadas
	 * así nomas
	 */
	public ExcepcionDelServidor disfrazar(Exception e) {
		return new ExcepcionDelServidor(e);
	}

	// TODO ver si es necesaria o se puede borrar
	public ExceptionDeConfiguracion errorEnConfiguracion(String message) {
		if (message == null)
			return new ExceptionDeConfiguracion();
		else
			return new ExceptionDeConfiguracion(message);
	}

	public ExcepcionDeUsuarioNoAutenticado usuarioNoAutenticado() {
		return new ExcepcionDeUsuarioNoAutenticado();
	}

	public ExcepcionDeLoginInvalido loginInvalido() {
		return new ExcepcionDeLoginInvalido();
	}

	public ExcepcionDeUsuarioYaLogueado usuarioYaLogueado() {
		return new ExcepcionDeUsuarioYaLogueado();
	}

}
