package ar.edu.utn.frba.proyecto.citysoft.excepciones;

/**
 * De aca deben derivar todas las excepciones que lleven mensajes al usuario.
 * Son seguramente excepciones que se producen como parte de las reglas del
 * negocio. Ejemplo, que un usuario quiera votar 2 veces el mismo contenido
 * 
 * @author Alejandro
 */
public class ExcepcionDeObjetoInexistente extends ExcepcionDelUsuario {

	public ExcepcionDeObjetoInexistente() {
		super();
	}

	public ExcepcionDeObjetoInexistente(String message) {
		super(message);
	}

}
