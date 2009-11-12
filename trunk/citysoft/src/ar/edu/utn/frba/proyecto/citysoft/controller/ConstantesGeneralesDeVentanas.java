package ar.edu.utn.frba.proyecto.citysoft.controller;

public interface ConstantesGeneralesDeVentanas {

	// **************************************
	// ** PARAMETROS DE SESION
	// **************************************

	String SESSION__USER_CONTEXT = "contextoUsuario";

	// **************************************
	// ** PAGINAS ZUL
	// **************************************

	String ZUL__SECURE_CLIENTE = "/secureCliente/";
	String ZUL__SECURE_OPERADOR = "/secureOperador/";
	String ZUL__SECURE_COMPARIDO = "/secureCompartido/";
	String ZUL__LOGIN = "/login.zul";
	String ZUL__NUEVO_VIAJE = ZUL__SECURE_COMPARIDO + "viajeNuevo.zul";

	// EXCLUSIVAS CLIENTE
	String ZUL__BIENVENIDO = ZUL__SECURE_CLIENTE + "bienvenido.zul";
	String ZUL__SEGUIDOR = ZUL__SECURE_CLIENTE + "follower.zul";
	String ZUL__SEGUIDOR_VIAJES_VIEJOS = ZUL__SECURE_CLIENTE + "seguidorDeViajesViejos.zul";

	// EXCLUSIVAS OPERADOR
	String ZUL__MENU_OPERADOR = ZUL__SECURE_OPERADOR + "barraMenu.zul";
	String ZUL__INTERFAZ_OPERADOR = ZUL__SECURE_OPERADOR + "interfazOperador.zul";
	String ZUL__ASIGNAR_VEHICULO = ZUL__SECURE_OPERADOR + "asignarVehiculo.zul";
	// EXCLUSIVSA OPERADOR: ALTAS
	String ZUL__ALTA_CLIENTE = ZUL__SECURE_OPERADOR + "altaCliente.zul";
	String ZUL__ALTA_CONDUCTOR = ZUL__SECURE_OPERADOR + "altaConductor.zul";
	String ZUL__ALTA_VEHICULO = ZUL__SECURE_OPERADOR + "altaVehiculo.zul";
	// EXCLUSIVSA OPERADOR: LISTAS
	String ZUL__LISTA_CLIENTES = ZUL__SECURE_OPERADOR + "listadoCliente.zul";
	String ZUL__LISTA_CONDUCTORES = ZUL__SECURE_OPERADOR + "listadoConductor.zul";
	String ZUL__LISTA_VEHICULOS = ZUL__SECURE_OPERADOR + "listadoVehiculo.zul";

	// **************************************
	// ** IMAGENES - Menú operador
	// **************************************

	String IMAGES__CITYSOFT_LOGO = "/images/citysoft_logo.jpg";
	String IMAGES__GTTR_LOGO = "/images/LogoGTTR.jpg";
	String IMAGES__CLIENTE = "/images/group.png";
	String IMAGES__CONDUCTOR = "/images/user.png";
	String IMAGES__VEHICULO = "/images/car.png";
	String IMAGES__LISTA = "/images/application_view_columns.png";
	String IMAGES__OPERADOR = "/images/user_business.png";
	String IMAGES__REFRESCAR = "/images/arrow_refresh.png";

	// **************************************
	// ** IMAGENES - Items de listas
	// **************************************

	String IMAGES__PIN = "/images/pin.png";
	String IMAGES__ADD = "/images/add.png";
	String IMAGES__CHECK = "/images/check.png";
	String IMAGES__CANCEL = "/images/cancel.png";
	String IMAGES__PLUG = "/images/plug.png";
	String IMAGES__UNPLUG = "/images/unplug.png";
	String IMAGES__LINK = "/images/link.png";
	String IMAGES__LINK_BREAK = "/images/link_break.png";
	String IMAGES__FLAG_BLUE = "/images/flag_blue.png";

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
