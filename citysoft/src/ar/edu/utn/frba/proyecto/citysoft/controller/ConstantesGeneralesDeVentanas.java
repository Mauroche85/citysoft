package ar.edu.utn.frba.proyecto.citysoft.controller;

public interface ConstantesGeneralesDeVentanas {

	// **************************************
	// ** PARAMETROS DE SESION
	// **************************************

	String SESSION__USER_CONTEXT = "contextoUsuario";

	// **************************************
	// ** PAGINAS ZUL
	// **************************************

	String ZUL__LOGIN = "/login.zul";
	String ZUL__BIENVENIDO = "/secure/bienvenido.zul";
	String ZUL__SEGUIDOR = "/secure/follower.zul";
	String ZUL__SEGUIDOR_VIAJES_VIEJOS = "/secure/seguidorDeViajesViejos.zul";
	String ZUL__NUEVO_VIAJE = "/viajeNuevo.zul";
	String ZUL__MENU_OPERADOR = "/barraMenu.zul";

	// **************************************
	// ** IMAGENES - Para items de listas
	// **************************************

	String IMAGES__PIN = "/images/pin.png";
	String IMAGES__ADD = "images/add.png";
	String IMAGES__CHECK = "images/check.png";
	String IMAGES__CANCEL = "images/cancel.png";
	String IMAGES__PLUG = "images/plug.png";
	String IMAGES__UNPLUG = "images/unplug.png";
	String IMAGES__LINK = "images/link.png";
	String IMAGES__LINK_BREAK = "images/link_break.png";
	String IMAGES__FLAG_BLUE = "images/flag_blue.png";

	// **************************************
	// ** IMAGENES - Gmarkers
	// **************************************

	String IMAGES__VEHICULO_MARKER = "/images/taxiMarker.png";
	String IMAGES__VEHICULO_OCUPADO_MARKER = "/images/taxiOcupadoMarker.png";
	String IMAGES__MARKER_FLAG = "/images/markerFlag.png";
	String IMAGES__MARKER_HOUSE = "/images/markerHouse.png";

	// **************************************
	// ** PREFIJOS
	// **************************************

	String GMARK_PREFIX = "gmark";

}
