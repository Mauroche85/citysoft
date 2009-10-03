package ar.edu.utn.frba.proyecto.citysoft.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ArchivoDeConfiguracion {

	private static final String ARCHIVO_CONFIGURACION = "citysoft.properties";
	private static final String PROPIEDAD__POBLAR_BASE = "gttr.poblarBase";

	// **************************************
	// ** Constructors
	// **************************************

	private ArchivoDeConfiguracion() {
	}

	private static ArchivoDeConfiguracion thiz;

	public static ArchivoDeConfiguracion getInstance() {
		if (thiz == null) {
			thiz = new ArchivoDeConfiguracion();
			thiz.InicializarConfiguracion();
		}
		return thiz;
	}

	private Properties properties;

	// **************************************
	// ** Execution
	// **************************************

	private void InicializarConfiguracion() {
		this.properties = new Properties();
		try {
			properties.load(new FileInputStream(ARCHIVO_CONFIGURACION));
		} catch (IOException e) {
			throw new RuntimeException("Error leyendo archivo de configuracion", e);
		}
	}

	public boolean getPoblarBase() {
		String poblarBase = this.properties.getProperty(PROPIEDAD__POBLAR_BASE, "false");
		if (poblarBase.equals("true") || poblarBase.equals("false")) {
			return Boolean.parseBoolean(poblarBase);
		} else {
			throw new RuntimeException(
					"El valor de configuracion para [poblar base] es incorrecto (" + poblarBase
							+ ")");
		}
	}

}
