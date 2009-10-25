package ar.edu.utn.frba.proyecto.citysoft.controller.interfazOperador;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public interface ConstantesInterfazOperador extends ConstantesGeneralesDeVentanas {

	// **************************************
	// ** ARCHIVOS ZUL
	// **************************************

	String ZUL__ACTIVAR_TAXI = "/activarTaxi.zul";
	String ZUL__CANCELAR_VIAJE = "/cancelarViaje.zul";
	String ZUL__ASIGNAR_TAXI = "/asignarTaxi.zul";
	String ZUL__LIBERAR_TAXI = "/liberarTaxi.zul";

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
