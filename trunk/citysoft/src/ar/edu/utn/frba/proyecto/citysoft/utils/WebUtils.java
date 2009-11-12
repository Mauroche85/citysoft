package ar.edu.utn.frba.proyecto.citysoft.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public class WebUtils implements ConstantesGeneralesDeVentanas {

	// **************************************
	// ** DENTRO DEL AMBIENTE DE FILTERS
	// **************************************

	public static String getContextPath(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getContextPath();
	}

	public static String getRequestUri(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI();
	}

	public static boolean sePidioUnaPaginaDeCliente(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI().startsWith(req.getContextPath() + ZUL__SECURE_CLIENTE);
	}

	public static boolean sePidioUnaPaginaDeOperador(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI().startsWith(req.getContextPath() + ZUL__SECURE_OPERADOR);
	}

	// **************************************
	// ** DENTRO DEL AMBIENTE ZK
	// **************************************

	/**
	 * Devuelve la URI (como URL pero sin http://www.sigaEseTaxi.com.ar) para el
	 * recurso recibido por parametro.
	 * 
	 * @param resource
	 *            es la URI del recurso relativa a la aplicacion. Esto quiere
	 *            decir que no tiene el contextPath. Ejemplo:
	 *            getUri("/secure/follower") = "/citysoft/secure/follower"
	 */
	public static String getUri(String resource) {
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		return WebUtils.getContextPath(req) + resource;
	}

}
