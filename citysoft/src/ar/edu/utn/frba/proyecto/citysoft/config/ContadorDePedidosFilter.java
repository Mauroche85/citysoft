package ar.edu.utn.frba.proyecto.citysoft.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public class ContadorDePedidosFilter implements ConstantesGeneralesDeVentanas, Filter {

	private int i;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.i = 0;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		LogFactory.getLog(this.getClass()).info(
				"Pedido " + (this.i++) + ": " + ((HttpServletRequest) req).getRequestURI());
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}

}
