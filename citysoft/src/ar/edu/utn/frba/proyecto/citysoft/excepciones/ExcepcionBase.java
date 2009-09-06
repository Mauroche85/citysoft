package ar.edu.utn.frba.proyecto.citysoft.excepciones;

/**
 * De aca deben derivar todas las excepciones del nuestra aplicación
 * 
 * @author Alejandro
 */
public abstract class ExcepcionBase extends RuntimeException {

	private static final long serialVersionUID = 618259099231150373L;

	public ExcepcionBase() {
		super();
	}

	public ExcepcionBase(String message) {
		super(message);
	}

	public ExcepcionBase(Exception e) {
		super(e);
	}

}
