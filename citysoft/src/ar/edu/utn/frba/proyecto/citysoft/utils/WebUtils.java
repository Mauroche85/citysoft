package ar.edu.utn.frba.proyecto.citysoft.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;

public class WebUtils {

	public static String getContextPath(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getContextPath();
	}

	public static String getRequestUri(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI();
	}

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
