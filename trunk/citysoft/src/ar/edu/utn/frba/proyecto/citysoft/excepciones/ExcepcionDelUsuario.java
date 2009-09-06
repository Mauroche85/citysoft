package ar.edu.utn.frba.proyecto.citysoft.excepciones;

/**
 * De aca deben derivar todas las excepciones que lleven mensajes al usuario.
 * Son seguramente excepciones que se producen como parte de las reglas del
 * negocio. Ejemplo, que un usuario quiera votar 2 veces el mismo contenido
 * 
 * @author Alejandro
 */
public class ExcepcionDelUsuario extends ExcepcionBase {

	private static final long serialVersionUID = 618259099231150373L;

	public ExcepcionDelUsuario() {
		super();
	}

	public ExcepcionDelUsuario(String message) {
		super(message);
	}

}
