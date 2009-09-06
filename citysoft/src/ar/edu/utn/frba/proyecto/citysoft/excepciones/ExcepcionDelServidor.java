package ar.edu.utn.frba.proyecto.citysoft.excepciones;

/**
 * De aca deben derivar todas las excepciones que parecerían ser errores del
 * servidor y el usuario deba ver
 * "Ha ocurrido un error interno. Intente nuevamente más tarde."
 * 
 * @author Alejandro
 */
public class ExcepcionDelServidor extends ExcepcionBase {

	private static final long serialVersionUID = -6567284352852448396L;

	public ExcepcionDelServidor() {
		super();
	}

	public ExcepcionDelServidor(String message) {
		super(message);
	}

	public ExcepcionDelServidor(Exception e) {
		super(e);
	}

}
