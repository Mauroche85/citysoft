package ar.edu.utn.frba.proyecto.citysoft.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public static String getContextPath(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getContextPath();
	}

	public static String getRequestUri(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI();
	}

}
