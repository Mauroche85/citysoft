package ar.edu.utn.frba.proyecto.citysoft.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.LogFactory;

public class ArchivoDeConfiguracion {

	private static final String ARCHIVO_CONFIGURACION = "citysoft.properties";
	private static final String PROPIEDAD__AUTENTICACION_ACTIVADA = "gttr.autenticacionActivada";
	private static final String PROPIEDAD__POBLAR_BASE = "gttr.poblarBase";
	private static final String PROPIEDAD__LOGIN_AUTOMATICO = "gttr.loginAutomatico";
	private static final String PROPIEDAD__SIMULACION = "gttr.simulacion";
	private static final String PROPIEDAD__INTERFAZ_OPERADOR_NUEVA = "gttr.interfazOperadorNueva";

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
			InputStream input = getClass().getClassLoader()
					.getResourceAsStream(ARCHIVO_CONFIGURACION);
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Error leyendo archivo de configuracion", e);
		}
	}

	public boolean getAutenticacionActivada() {
		String autenticacionActivada = this.properties.getProperty(PROPIEDAD__AUTENTICACION_ACTIVADA,
				"true");
		if (autenticacionActivada.equals("true") || autenticacionActivada.equals("false")) {
			return Boolean.parseBoolean(autenticacionActivada);
		} else {
			throw new RuntimeException("El valor de configuracion para [autenticacion activada]"
					+ " es incorrecto (" + autenticacionActivada + ")");
		}
	}

	public boolean getPoblarBase() {
		String poblarBase = this.properties.getProperty(PROPIEDAD__POBLAR_BASE, "false");
		if (poblarBase.equals("true") || poblarBase.equals("false")) {
			return Boolean.parseBoolean(poblarBase);
		} else if (poblarBase.equals("once")) {
			desactivarPoblarBase();
			return true;
		} else {
			throw new RuntimeException("El valor de configuracion para [poblar base] es incorrecto ("
					+ poblarBase + ")");
		}
	}

	public boolean getLoginAutomatico() {
		String loginAutomatico = this.properties.getProperty(PROPIEDAD__LOGIN_AUTOMATICO, "false");
		if (loginAutomatico.equals("true") || loginAutomatico.equals("false")) {
			return Boolean.parseBoolean(loginAutomatico);
		} else {
			throw new RuntimeException(
					"El valor de configuracion para [login automático] es incorrecto ("
							+ loginAutomatico + ")");
		}
	}

	public List<Integer> getIdsVehiculosSimulados(String nombreSimulacion) {
		String vehiculosSimulados = this.properties.getProperty(PROPIEDAD__SIMULACION + "."
				+ nombreSimulacion, "");

		List<Integer> l = new ArrayList<Integer>();
		String[] split = vehiculosSimulados.split(",");
		for (int i = 0; i < split.length; i++) {
			l.add(i, Integer.parseInt(split[i]));
		}
		return l;
	}

	public boolean getInterfazOperadorNueva() {
		String nueva = this.properties.getProperty(PROPIEDAD__INTERFAZ_OPERADOR_NUEVA, "true");
		if (nueva.equals("true") || nueva.equals("false")) {
			return Boolean.parseBoolean(nueva);
		} else {
			throw new RuntimeException(
					"El valor de configuracion para [interfaz operador nueva] es incorrecto ("
							+ nueva + ")");
		}
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private void desactivarPoblarBase() {
		try {
			this.properties.setProperty(PROPIEDAD__POBLAR_BASE, "false");
			URL url = getClass().getClassLoader().getResource(ARCHIVO_CONFIGURACION);
			LogFactory.getLog(this.getClass()).info("Vamos a desactivar el poblar base en " + url);
			OutputStream out = new FileOutputStream(url.getFile());
			properties.store(out, "Se cambio poblarBase=once por poblarBase=false");
			out.close();
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error("Error actualizando configuración poblarBase");
			throw new RuntimeException(e);
		}
	}

}
