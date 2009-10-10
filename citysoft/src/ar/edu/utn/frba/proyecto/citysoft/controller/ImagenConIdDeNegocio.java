package ar.edu.utn.frba.proyecto.citysoft.controller;

import org.zkoss.zul.Image;

/**
 * Es un elemento <image> comun y corriente para paginas ZUL, solo que permite
 * usar el atributo "idNegocio" como si fuera un custom-attribute.
 * 
 * La razon por la que usamos esto en lugar de un custom-attribute, es que al
 * parecer los custom-attributes no soportan tomar valor desde variables
 * originadas en el databinder.
 * 
 * @see listadoCliente.zul
 * @author Alejandro
 */
public class ImagenConIdDeNegocio extends Image {

	private static final long serialVersionUID = 3163353167068288629L;

	// **************************************
	// ** EJECUCION
	// **************************************

	public Object idNegocio;

	public Object getIdNegocio() {
		return this.idNegocio;
	}

	public void setIdNegocio(int idNegocio) {
		this.idNegocio = idNegocio;
	}

}
