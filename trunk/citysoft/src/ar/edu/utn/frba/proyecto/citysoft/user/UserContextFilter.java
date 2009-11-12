package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import ar.edu.utn.frba.proyecto.citysoft.config.ArchivoDeConfiguracion;
import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public class UserContextFilter implements ConstantesGeneralesDeVentanas, Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		UserContext userContext = (UserContext) session.getAttribute(SESSION__USER_CONTEXT);
		// Si es la primera vez que pasa por aca, el userContext es nulo. Por
		// tanto, lo creamos
		if (userContext == null) {
			userContext = new UserContext();
			session.setAttribute(SESSION__USER_CONTEXT, userContext);
		}
		// Ahora, ponemos a disponibilidad del thread el userContext posta
		UserContext.setUserContext(userContext);
		// Previo a seguir, hacemos el login automatico
		doAutoLogin(request);
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	// **************************************
	// ** Helpers
	// **************************************

	/**
	 * Filtramos del proceso de autologin, la pagina de login propiamente dicha.
	 * Para ello, debemos filtrar todos los requests al motor ZUL porque no
	 * sabemos que consultas a dicho motor son para el login.zul y cuales no!!!
	 */
	private void doAutoLogin(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (!sePidioLaPaginaDeLogin(request, uri)
				&& ArchivoDeConfiguracion.getInstance().getLoginAutomatico()
				&& !UserContext.getUserContext().isUsuarioAutenticado()) {
			UserContext.getUserContext().login("ctomassino", "citysoft");
		}
	}

	private boolean sePidioLaPaginaDeLogin(HttpServletRequest request, String uri) {
		return StringUtils.equals(uri, request.getContextPath() + ZUL__LOGIN);
	}

}
