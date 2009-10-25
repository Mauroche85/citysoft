package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public interface ConstantesInterfazOperador extends ConstantesGeneralesDeVentanas {

	// **************************************
	// ** ARCHIVOS ZUL
	// **************************************

	String ZUL__ACTIVAR_VEHICULO = "/activarTaxi.zul";
	String ZUL__DESACTIVAR_VEHICULO = "/desactivarTaxi.zul";
	String ZUL__CANCELAR_VIAJE = "/cancelarViaje.zul";
	String ZUL__ASIGNAR_VEHICULO = "/asignarTaxi.zul";
	String ZUL__LIBERAR_VEHICULO = "/liberarTaxi.zul";

	// **************************************
	// ** COMPONENTES
	// **************************************

	String COMP__WIN_OPERADOR = "winOperador";
	String COMP__GMAP_OPERADOR = "gmapOperador";

	// **************************************
	// ** PARAMETROS (?)
	// **************************************

	String CONTEXT_PARAM__QUIEN_ABRIO_EL_POPUP = "quienAbrioElPopup";

}
