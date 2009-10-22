package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.utils.WebUtils;

public class AuthenticationFilter implements Filter, ConstantesGeneralesDeVentanas {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		if (!UserContext.getUserContext().isUsuarioAutenticado()) {
			((HttpServletResponse) res).sendRedirect(WebUtils.getContextPath(req) + ZUL__LOGIN
					+ "?uri=" + WebUtils.getRequestUri(req));
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
