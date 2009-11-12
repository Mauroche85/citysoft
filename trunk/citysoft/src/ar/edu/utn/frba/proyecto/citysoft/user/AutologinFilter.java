package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ar.edu.utn.frba.proyecto.citysoft.config.ArchivoDeConfiguracion;
import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.utils.WebUtils;

/**
 * Este filtro, filtra todas las páginas ZUL para interceptar pedidos a
 * cualquier página, y si estamos en pedidos del operador, se autologuea como
 * operador; mientras que si estamos ante pedidos del cliente, se autologuea
 * como tomassino
 */
public class AutologinFilter implements ConstantesGeneralesDeVentanas, Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		//
		if (ArchivoDeConfiguracion.getInstance().getLoginAutomatico()
				&& !UserContext.getUserContext().isUsuarioAutenticado()) {
			if (WebUtils.sePidioUnaPaginaDeCliente(req)) {
				UserContext.getUserContext().login("ctomassino", "citysoft");
			} else if (WebUtils.sePidioUnaPaginaDeOperador(req)) {
				UserContext.getUserContext().login("operador", "operador");
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
